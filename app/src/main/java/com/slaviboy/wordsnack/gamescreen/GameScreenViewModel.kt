package com.slaviboy.wordsnack.gamescreen

import android.animation.ValueAnimator
import android.content.res.AssetManager
import android.graphics.PointF
import android.util.Log
import android.view.MotionEvent
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.slaviboy.composeunits.DpToPx
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.core.allTrue
import com.slaviboy.wordsnack.core.distanceBetweenTwoPoints
import com.slaviboy.wordsnack.extensions.getRandomItems
import com.slaviboy.wordsnack.extensions.hasSameChars
import com.slaviboy.wordsnack.extensions.positionBetweenPivotAtDistance
import com.slaviboy.wordsnack.extensions.readAsClipData
import com.slaviboy.wordsnack.extensions.readAsImageBitmap
import com.slaviboy.wordsnack.extensions.readAsText
import com.slaviboy.wordsnack.extensions.rotateAroundPivot
import com.slaviboy.wordsnack.extensions.setCurveThroughPoints
import com.slaviboy.wordsnack.extensions.shuffle
import com.slaviboy.wordsnack.interpolators.Ease
import com.slaviboy.wordsnack.interpolators.EasingInterpolator
import com.slaviboy.wordsnack.preferences.ApplicationPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject
import kotlin.random.Random

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
    var leafBoxWidth by mutableStateOf(0.dw)
    var letterBoxPaddingHorizontal by mutableStateOf(0.dw)
    var letterBoxPaddingVertical by mutableStateOf(0.dw)

    var allowedLettersBoxWidth by mutableStateOf(0.9.dw)
    var allowedLettersBoxHeight by mutableStateOf(0.8.dw)
    var allowedLettersWidth by mutableStateOf(0.18.dw)
    var allowedLettersAngles by mutableStateOf<List<Float>>(listOf())
    var allowedLettersPosition by mutableStateOf<List<DpOffset>>(listOf())

    private var passThroughCurvePoints: MutableList<PointF> = mutableListOf()
    private var passThroughSelectedLettersIndices: MutableList<Int> = mutableListOf()
    var passThroughSelectedLetters by mutableStateOf<List<Char>>(listOf())
    var passThroughPath by mutableStateOf(Path())
    var passThroughSelectedLetterWidth by mutableStateOf(0.115.dw)
    var passThroughSelectedLettersBoxWidth by mutableStateOf(0.dw)

    private val minNumberOfLetters = 2
    private val maxNumberOfLetters = 9
    private val maxNumberOfWords = 4

    private val currentLevel = 1
    private var fullWordsList: List<CharArray> = listOf()

    private val pivot = DpOffset(0.dw, 0.dw)

    private fun adjustLetterBox(words: List<String>) {
        val numberOfLetterRows = words.size
        val numberOfLetterColumns = words.maxBy { it.length }.length
        letterBoxPaddingHorizontal = 0.006.dw + 0.001.dw * (maxNumberOfLetters - numberOfLetterColumns)
        letterBoxPaddingVertical = 0.013.dw
        letterBoxWidth = calculateLetterBoxWidth(numberOfLetterRows, numberOfLetterColumns)
        leafBoxWidth = letterBoxWidth * 0.27f
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
            Log.i("jojo", "${words.joinToString(" - ")}")
            //val words = listOf("ok", "snakes") //listOf("pork", "anaconda", "dog", "snake", "bubblegum")
            adjustLetterBox(words)

            this@GameScreenViewModel.words = words
        }
    }

    var generatedWord: String = ""

    fun generateAllowedLetters() {
        generatedWord = "чобанин"
        shuffle()

        allowedLettersPosition.forEachIndexed { i, dpOffset ->
            shufflePosition[i] = dpOffset
        }
        allowedLettersAngles.forEachIndexed { i, float ->
            shuffleAngles[i] = float
        }
    }

    fun shuffleAllowedLetters() {
        shuffleAnimator.start()
    }

    fun requestHint() {
    }

    private fun shuffle() {
        allowedLetters = generatedWord.shuffle().toCharArray()
        allowedLettersWidth = 0.16.dw + 0.07.dw * (1f - allowedLetters.size / maxNumberOfLetters.toFloat())

        val minDistanceFromPivot = allowedLettersWidth * 0.95f
        val distanceFromPivot = minDistanceFromPivot + 0.2.dw * (allowedLetters.size / maxNumberOfLetters.toFloat())
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
            }.toFloat()
            angles.add(angle)
            positions.add(rotatePosition)
        }
        allowedLettersPosition = positions
        allowedLettersAngles = angles
    }

    private var passThroughScaleAnimators = Array<ValueAnimator>(maxNumberOfLetters) { i ->
        ValueAnimator.ofFloat(1f, 1.07f).apply {
            duration = 250
            interpolator = EasingInterpolator(Ease.BounceInOut)
            addUpdateListener {
                passThroughScale[i] = it.animatedValue as Float
            }
        }
    }

    private var passThroughShakeAnimators = Array<ValueAnimator>(maxNumberOfLetters) { i ->
        ValueAnimator.ofFloat(0f, 1f, 0f, -1f, 0f).apply {
            duration = 250
            interpolator = EasingInterpolator(Ease.BackInOut)
            addUpdateListener {
                val value = (it.animatedValue as Float)
                passThroughTranslate[i] = value.dp * 1f
                passThroughRotate[i] = value * 12f
            }
        }
    }

    val passThroughTranslate = mutableStateListOf<Dp>(
        *Array(maxNumberOfLetters) {
            1.dp
        }
    )

    val passThroughRotate = mutableStateListOf<Float>(
        *FloatArray(maxNumberOfLetters) {
            1f
        }.toTypedArray()
    )

    private val numberOfLeafs = 8

    val leafsAnimatedPositions = mutableStateListOf<DpOffset>(
        *Array(numberOfLeafs) {
            DpOffset(0.dw, 0.dw)
        }
    )

    val leafsAnimatedOpacity = mutableStateListOf<Float>(
        *Array(numberOfLeafs) {
            1f
        }
    )

    val leafsAnimatedScale = mutableStateListOf<Float>(
        *Array(numberOfLeafs) {
            1f
        }
    )

    val leafsAngles = mutableStateListOf<Float>(
        *FloatArray(numberOfLeafs) {
            1f
        }.toTypedArray()
    )


    var answerLettersScale by mutableStateOf<Float>(1f)

    private var answerLettersAnimator = ValueAnimator.ofFloat(-0.7f, 1f).apply {
        duration = 1000
        interpolator = EasingInterpolator(Ease.ElasticOut)
        addUpdateListener {
            answerLettersScale = it.animatedValue as Float
        }
    }

    private var leafsPositions: MutableList<DpOffset> = mutableListOf()
    private var leafAnimator = ValueAnimator.ofFloat(0f, 1.3f).apply {
        duration = 1200
        interpolator = EasingInterpolator(Ease.Linear)
        addUpdateListener {
            val value = it.animatedValue as Float
            for (i in leafsPositions.indices) {
                leafsAnimatedPositions[i] = leafsPositions[i]
                    .positionBetweenPivotAtDistance(
                        pivot = pivot,
                        factor = value
                    )
                leafsAnimatedOpacity[i] = if (value < 0.8f) 1f else 1f - value
                leafsAnimatedScale[i] = if (value < 0.5f) 1f else 1.1f - value
                leafsAnimatedOpacity[i] = if (value < 0.6f) 1f else 1f - value
            }
        }
        doOnStart {
            leafsPositions.clear()
            for (i in 0 until numberOfLeafs) {
                val distanceFromPivot = (letterBoxWidth / 2f) + (letterBoxPaddingHorizontal * 4f) * Random.nextFloat()
                val position = DpOffset(pivot.x, pivot.y - distanceFromPivot)
                val angleAroundPivot = (i / numberOfLeafs.toFloat()) * 360f// * (10 * Random.nextFloat())
                val rotatePosition = position.rotateAroundPivot(pivot, angleAroundPivot)
                leafsPositions.add(rotatePosition)
                leafsAngles[i] = angleAroundPivot
            }
        }
    }


    private var shuffleAnimator = ValueAnimator.ofFloat(1f, 0f, 1f).apply {
        duration = 700
        addUpdateListener {
            val value = it.animatedValue as Float
            for (i in allowedLetters.indices) {
                shufflePosition[i] = allowedLettersPosition[i]
                    .positionBetweenPivotAtDistance(
                        pivot = pivot,
                        factor = value
                    )
                    .rotateAroundPivot(
                        pivot = pivot,
                        angle = (1f - value) * 180f
                    )
                shuffleAngles[i] = (1f - value) * 130f * allowedLettersAngles[i]
            }
            if (it.currentPlayTime > it.duration / 2 &&
                !isShuffleAnimating
            ) {
                shuffle()
                isShuffleAnimating = true
            }
        }
        doOnEnd {
            isShuffleAnimating = false
        }
    }

    private var isShuffleAnimating: Boolean = false

    val passThroughScale = mutableStateListOf<Float>(
        *FloatArray(maxNumberOfLetters) {
            1f
        }.toTypedArray()
    )

    val shufflePosition = mutableStateListOf<DpOffset>(
        *Array(maxNumberOfLetters) {
            DpOffset(0.dw, 0.dw)
        }
    )

    val shuffleAngles = mutableStateListOf<Float>(
        *FloatArray(maxNumberOfLetters) {
            1f
        }.toTypedArray()
    )

    private fun checkForCorrectPassThrough(index: Int) {
        val passThoughWord = passThroughSelectedLetters.joinToString(separator = "")
        val i = words.indexOf(passThoughWord)
        val isMatch = (i != -1) && !passThroughAnswerIndices.contains(i)
        passThroughAnswerIndex = i
        if (isMatch) {
            passThroughAnswerIndices.add(i)
            leafAnimator.start()
            answerLettersAnimator.start()
        } else {
            passThroughShakeAnimators[index].apply {
                start()
            }
        }
    }

    val passThroughAnswerIndices = mutableStateListOf<Int>()
    var passThroughAnswerIndex by mutableStateOf(-1)

    fun onMotionEvent(motionEvent: MotionEvent) {

        fun changeSelectedLetters(): Pair<Int, Boolean> {
            val x1 = motionEvent.x
            val y1 = motionEvent.y
            var pair = Pair(-1, false)
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
                        pair = Pair(i, true)
                        points.add(point)
                        passThroughSelectedLettersIndices.add(i)
                        passThroughSelectedLetters = selectedLetters.also { it.add(allowedLetters[i]) }
                    } else if (points.size > 1 && points[points.size - 2] == point) {
                        pair = Pair(passThroughSelectedLettersIndices.last(), false)
                        points.removeLast()
                        passThroughSelectedLettersIndices.removeLast()
                        passThroughSelectedLetters = selectedLetters.also { it.removeLast() }
                    }
                    break
                }
            }

            passThroughPath = Path().apply {
                val points = passThroughCurvePoints.toMutableList()
                setCurveThroughPoints(points.also { it.add(PointF(x1, y1)) })
            }

            return pair
        }

        fun clearSelectedLetters() {
            passThroughCurvePoints = mutableListOf()
            passThroughSelectedLetters = listOf()
            passThroughPath = Path()
            passThroughSelectedLettersIndices = mutableListOf()
        }

        when (motionEvent.action) {
            MotionEvent.ACTION_UP -> {
                passThroughSelectedLettersIndices.forEach {
                    passThroughScaleAnimators[it].apply {
                        setFloatValues(1.08f, 0.95f, 1f)
                        interpolator = EasingInterpolator(Ease.CubicOut)
                        start()
                    }
                    checkForCorrectPassThrough(it)
                }
                clearSelectedLetters()
            }

            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_MOVE -> {
                val (index, isAdded) = changeSelectedLetters()
                if (index == -1) return
                passThroughScaleAnimators[index].apply {
                    if (isAdded) {
                        setFloatValues(1f, 1.19f, 1.09f)
                    } else {
                        setFloatValues(1.09f, 0.95f, 1f)
                    }
                    start()
                }
            }
        }
        val passThroughSelectedLettersBoxOffset = 0.12.dw
        passThroughSelectedLettersBoxWidth = (passThroughSelectedLetterWidth *
                Math.max(2, passThroughSelectedLetters.size) +
                passThroughSelectedLettersBoxOffset)
        showAllowedLettersResult.targetState = passThroughCurvePoints.isNotEmpty()
    }
}