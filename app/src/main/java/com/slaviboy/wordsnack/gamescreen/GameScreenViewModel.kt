package com.slaviboy.wordsnack.gamescreen

import android.content.res.AssetManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.min
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.extensions.getRandomItems
import com.slaviboy.wordsnack.extensions.hasSameChars
import com.slaviboy.wordsnack.extensions.readAsClipData
import com.slaviboy.wordsnack.extensions.readAsImageBitmap
import com.slaviboy.wordsnack.extensions.readAsText
import com.slaviboy.wordsnack.extensions.rotateAroundPivot
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

    private fun allTrue(vararg booleans: Boolean): Boolean {
        return booleans.all { it }
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
        allowedLetters = "чобан".toCharArray() // приятели
        allowedLettersWidth = 0.14.dw + 0.07.dw * (1f - allowedLetters.size / maxNumberOfLetters.toFloat())

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


    var allowedLettersBoxWidth by mutableStateOf(0.9.dw)
    var allowedLettersBoxHeight by mutableStateOf(0.8.dw)
    var allowedLettersWidth by mutableStateOf(0.18.dw)
    var allowedLettersAngles by mutableStateOf<List<Float>>(listOf())
    var allowedLettersPosition by mutableStateOf<List<DpOffset>>(listOf())

}