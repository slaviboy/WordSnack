package com.slaviboy.wordsnack.gamescreen

import android.content.res.AssetManager
import android.graphics.PointF
import android.view.MotionEvent
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.min
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.slaviboy.composeunits.DpToPx
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.core.allTrue
import com.slaviboy.wordsnack.core.distanceBetweenTwoPoints
import com.slaviboy.wordsnack.extensions.getRandomItems
import com.slaviboy.wordsnack.extensions.hasSameChars
import com.slaviboy.wordsnack.extensions.readAsClipData
import com.slaviboy.wordsnack.extensions.readAsImageBitmap
import com.slaviboy.wordsnack.extensions.readAsText
import com.slaviboy.wordsnack.extensions.rotateAroundPivot
import com.slaviboy.wordsnack.extensions.setCurveThroughPoints
import com.slaviboy.wordsnack.preferences.ApplicationPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val gson: Gson,
    private val assetManager: AssetManager,
    private val applicationPreferences: ApplicationPreferences
) : ViewModel() {

    var showAllowedLettersResult = MutableTransitionState(false)

    val backgroundImageBitmap by mutableStateOf(
        assetManager.readAsImageBitmap("background_game")
    )

    val commonClipData by mutableStateOf(
        assetManager.readAsClipData(gson, "common")
    )

    var words by mutableStateOf<List<String>>(listOf())
    var allowedLetters by mutableStateOf(charArrayOf())

    var letterBoxWidth by mutableStateOf(0.dw)
    var letterBoxPaddingHorizontal by mutableStateOf(0.dw)
    var letterBoxPaddingVertical by mutableStateOf(0.dw)

    var allowedLettersBoxWidth by mutableStateOf(0.9.dw)
    var allowedLettersBoxHeight by mutableStateOf(0.8.dw)
    var allowedLettersWidth by mutableStateOf(0.18.dw)
    var allowedLettersAngles by mutableStateOf<List<Float>>(listOf())
    var allowedLettersPosition by mutableStateOf<List<DpOffset>>(listOf())

    private var passThroughCurvePoints: MutableList<PointF> = mutableListOf()
    var passThroughSelectedLetters by mutableStateOf<List<Char>>(listOf())
    var passThroughPath by mutableStateOf(Path())

    private val minNumberOfLetters = 2
    private val maxNumberOfLetters = 9
    private val maxNumberOfWords = 4

    private val currentLevel = 1
    private var fullWordsList: List<CharArray> = listOf()

    private fun adjustLetterBox(words: List<String>) {
        val numberOfLetterRows = words.size
        val numberOfLetterColumns = words.maxBy { it.length }.length
        letterBoxPaddingHorizontal = 0.006.dw + 0.001.dw * (maxNumberOfLetters - numberOfLetterColumns)
        letterBoxPaddingVertical = 0.013.dw
        letterBoxWidth = calculateLetterBoxWidth(numberOfLetterRows, numberOfLetterColumns)
    }

    private fun calculateLetterBoxWidth(
        numberOfLetterRows: Int,
        numberOfLetterColumns: Int
    ): Dp {
        val width = 0.8.dw - (letterBoxPaddingHorizontal * 2f * numberOfLetterColumns)
        val height = 0.55.dw - (letterBoxPaddingVertical * 2f * numberOfLetterRows)
        val sizePerHeight = height / numberOfLetterRows
        val sizePerWidth = width / numberOfLetterColumns
        val minSizePer = min(sizePerHeight, sizePerWidth)
        return min(minSizePer, 0.16.dw)
    }

    fun changeLanguage(locale: Locale) {
        applicationPreferences.setLocale(locale)

        val wordsFileName = if (locale.language == "bg") {
            "bulgarian_bg"
        } else {
            "english_en"
        }
        fullWordsList = assetManager.readAsText(
            fileName = wordsFileName,
            filePath = "words/",
            fileType = "txt"
        ).split("\n")
            .map {
                it.toCharArray()
            }
    }

    fun generateWords() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            val matchesList = mutableListOf<String>()
            for (i in fullWordsList.indices) {
                val hasSameChars = fullWordsList[i].hasSameChars(allowedLetters)
                if (hasSameChars) {
                    matchesList.add(String(fullWordsList[i]))
                }
            }
            val filteredWords = matchesList
                .filter {
                    allTrue(
                        it.isNotEmpty(),
                        it.length >= minNumberOfLetters,
                        it.length <= maxNumberOfLetters
                    )
                }
            val words = filteredWords.getRandomItems(maxNumberOfWords)
            //val words = listOf("ok", "snakes") //listOf("pork", "anaconda", "dog", "snake", "bubblegum")
            adjustLetterBox(words)

            this@GameScreenViewModel.words = words
        }
    }

    fun generateAllowedLetters() {
        allowedLetters = "чобанин".toCharArray() // приятели
        allowedLettersWidth = 0.16.dw + 0.07.dw * (1f - allowedLetters.size / maxNumberOfLetters.toFloat())

        val minDistanceFromPivot = allowedLettersWidth * 0.95f
        val distanceFromPivot = minDistanceFromPivot + 0.2.dw * (allowedLetters.size / maxNumberOfLetters.toFloat())
        val pivot = DpOffset(0.dw, 0.dw)
        val position = DpOffset(pivot.x, pivot.y - distanceFromPivot)

        val angles = mutableListOf<Float>()
        val positions = mutableListOf<DpOffset>()
        allowedLetters.forEachIndexed { i, _ ->
            val angleAroundPivot = (i.toFloat() / allowedLetters.size) * 360f
            val rotatePosition = position.rotateAroundPivot(pivot, angleAroundPivot)
            val angle = if (angleAroundPivot > 180f) {
                (0..9).random()
            } else {
                -(0..9).random()
            }
            angles.add(angle.toFloat())
            positions.add(rotatePosition)
        }
        allowedLettersPosition = positions
        allowedLettersAngles = angles
    }

    fun scrambleAllowedLetters() {

    }

    fun onMotionEvent(motionEvent: MotionEvent) {
        when (motionEvent.action) {
            MotionEvent.ACTION_UP -> {
                passThroughCurvePoints = mutableListOf()
                passThroughSelectedLetters = listOf()
                passThroughPath = Path()
            }

            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                val x1 = motionEvent.x
                val y1 = motionEvent.y
                for (i in allowedLettersPosition.indices) {
                    val position = allowedLettersPosition[i]
                    val x2 = (position.x + allowedLettersBoxWidth / 2f).value.DpToPx
                    val y2 = (position.y + allowedLettersBoxHeight / 2f).value.DpToPx

                    val radius = allowedLettersWidth.value.DpToPx * 0.6f
                    val isInside = distanceBetweenTwoPoints(x1, y1, x2, y2) < radius
                    if (isInside) {
                        val point = PointF(x2, y2)
                        val points = passThroughCurvePoints
                        val selectedLetters = passThroughSelectedLetters.toMutableList()
                        if (!points.contains(point)) {
                            points.add(point)
                            passThroughSelectedLetters = selectedLetters.also { it.add(allowedLetters[i]) }
                        } else if (points.size > 1 && points[points.size - 2] == point) {
                            points.removeLast()
                            passThroughSelectedLetters = selectedLetters.also { it.removeLast() }
                        }
                        break
                    }
                }

                passThroughPath = Path().apply {
                    val points = passThroughCurvePoints.toMutableList()
                    setCurveThroughPoints(points.also { it.add(PointF(x1, y1)) })
                }
            }
        }
        showAllowedLettersResult.targetState = passThroughCurvePoints.isNotEmpty()
    }
}