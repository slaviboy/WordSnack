package com.slaviboy.wordsnack.interpolators

import com.slaviboy.wordsnack.core.Math.PI
import com.slaviboy.wordsnack.core.Math.abs
import com.slaviboy.wordsnack.core.Math.asin
import com.slaviboy.wordsnack.core.Math.cos
import com.slaviboy.wordsnack.core.Math.pow
import com.slaviboy.wordsnack.core.Math.sin
import com.slaviboy.wordsnack.core.Math.sqrt
import com.slaviboy.wordsnack.core.multiply
import com.slaviboy.wordsnack.interpolators.Ease.*

/**
 * The Easing class provides a collection of ease functions. It does not use the standard 4 param
 * ease signature. Instead it uses a single param which indicates the current linear ratio (0 to 1) of the tween.
 */
object EasingProvider {

    private const val pi2 = PI * 2f

    /**
     * @param ease Easing type
     * @param elapsedTimeRate Elapsed time / Total time
     * @return easedValue
     */
    operator fun get(ease: Ease, elapsedTimeRate: Float): Float {
        return when (ease) {
            is Linear -> elapsedTimeRate
            is QuadIn -> getPowIn(elapsedTimeRate, 2.0f)
            is QuadOut -> getPowOut(elapsedTimeRate, 2.0f)
            is QuadInOut -> getPowInOut(elapsedTimeRate, 2.0f)
            is CubicIn -> getPowIn(elapsedTimeRate, 3.0f)
            is CubicOut -> getPowOut(elapsedTimeRate, 3.0f)
            is CubicInOut -> getPowInOut(elapsedTimeRate, 3.0f)
            is QuartIn -> getPowIn(elapsedTimeRate, 4.0f)
            is QuartOut -> getPowOut(elapsedTimeRate, 4.0f)
            is QuartInOut -> getPowInOut(elapsedTimeRate, 4.0f)
            is QuintIn -> getPowIn(elapsedTimeRate, 5.0f)
            is QuintOut -> getPowOut(elapsedTimeRate, 5.0f)
            is QuintInOut -> getPowInOut(elapsedTimeRate, 5.0f)
            is SineIn -> (1f - cos(elapsedTimeRate * PI / 2f))
            is SineOut -> sin(elapsedTimeRate * PI / 2f)
            is SineInOut -> (-0.5f * cos(PI * elapsedTimeRate) - 1f)
            is BackIn -> elapsedTimeRate * elapsedTimeRate * ((1.7f + 1f) * elapsedTimeRate - 1.7f)
            is BackOut -> {
                val elapsedTimeRate = elapsedTimeRate - 1
                (elapsedTimeRate * elapsedTimeRate * ((1.7f + 1f) * elapsedTimeRate + 1.7f) + 1f)
            }

            BackInOut -> getBackInOut(elapsedTimeRate, 1.7f)
            CircIn -> -(sqrt(1f - elapsedTimeRate * elapsedTimeRate) - 1)
            CircOut -> {
                val elapsedTimeRate = elapsedTimeRate - 1
                sqrt(1f - elapsedTimeRate * elapsedTimeRate)
            }

            CircInOut -> {
                var elapsedTimeRate = elapsedTimeRate * 2f
                if (elapsedTimeRate < 1f) {
                    (-0.5f * (sqrt((1f - elapsedTimeRate * elapsedTimeRate)) - 1f)).toFloat()
                } else {
                    elapsedTimeRate -= 2f
                    (0.5f * (sqrt((1f - elapsedTimeRate * elapsedTimeRate)) + 1f)).toFloat()
                }
            }

            BounceIn -> getBounceIn(elapsedTimeRate)
            BounceOut -> getBounceOut(elapsedTimeRate)
            BounceInOut -> {
                if (elapsedTimeRate < 0.5f) {
                    getBounceIn(elapsedTimeRate * 2f) * 0.5f
                } else {
                    getBounceOut(elapsedTimeRate * 2f - 1f) * 0.5f + 0.5f
                }
            }

            ElasticIn -> getElasticIn(elapsedTimeRate, 1.0f, 0.3f)
            ElasticOut -> getElasticOut(elapsedTimeRate, 1.0f, 0.3f)
            ElasticInOut -> getElasticInOut(elapsedTimeRate, 1.0f, 0.45f)
            EaseInExpo -> {
                pow(2.0f, 10f * (elapsedTimeRate - 1f))
            }

            EaseOutExpo -> {
                -pow(2.0f, (-10f * elapsedTimeRate)) + 1
            }

            EaseInOutExpo -> {
                var elapsedTimeRate = elapsedTimeRate * 2f
                if (elapsedTimeRate < 1) {
                    pow(2.0f, 10f * (elapsedTimeRate - 1f)) * 0.5f
                } else {
                    elapsedTimeRate -= 1f
                    (-pow(2.0f, -10f * elapsedTimeRate) + 2f) * 0.5f
                }
            }

            else -> elapsedTimeRate
        }
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param pow pow The exponent to use (ex. 3 would return a cubic ease).
     * @return easedValue
     */
    private fun getPowIn(elapsedTimeRate: Float, pow: Float): Float {
        return pow(elapsedTimeRate, pow)
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param pow pow The exponent to use (ex. 3 would return a cubic ease).
     * @return easedValue
     */
    private fun getPowOut(elapsedTimeRate: Float, pow: Float): Float {
        return 1f - pow((1 - elapsedTimeRate), pow)
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param pow pow The exponent to use (ex. 3 would return a cubic ease).
     * @return easedValue
     */
    private fun getPowInOut(elapsedTimeRate: Float, pow: Float): Float {
        var elapsedTimeRate = elapsedTimeRate
        return if (2.let { elapsedTimeRate *= it; elapsedTimeRate } < 1) {
            (0.5f * pow(elapsedTimeRate, pow))
        } else {
            (1f - 0.5f * abs(pow((2f - elapsedTimeRate), pow)))
        }
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amount amount The strength of the ease.
     * @return easedValue
     */
    private fun getBackInOut(elapsedTimeRate: Float, amount: Float): Float {
        var elapsedTimeRate = elapsedTimeRate
        var amount = amount
        amount *= 1.525f
        return if (2.let { elapsedTimeRate *= it; elapsedTimeRate } < 1) {
            (0.5f * (elapsedTimeRate * elapsedTimeRate * ((amount + 1) * elapsedTimeRate - amount)))
        } else (0.5f * (2.let { elapsedTimeRate -= it; elapsedTimeRate } * elapsedTimeRate * ((amount + 1) * elapsedTimeRate + amount) + 2))
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @return easedValue
     */
    private fun getBounceIn(elapsedTimeRate: Float): Float {
        return 1f - getBounceOut(1f - elapsedTimeRate)
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @return easedValue
     */
    private fun getBounceOut(elapsedTimeRate: Float): Float {
        var elapsedTimeRate = elapsedTimeRate
        return if (elapsedTimeRate < 1f / 2.75f) {
            (7.5625f * elapsedTimeRate * elapsedTimeRate)
        } else if (elapsedTimeRate < 2f / 2.75f) {
            elapsedTimeRate -= 1.5f / 2.75f
            7.5625f * elapsedTimeRate * elapsedTimeRate + 0.75f
        } else if (elapsedTimeRate < 2.5f / 2.75f) {
            elapsedTimeRate -= 2.25f / 2.75f
            7.5625f * elapsedTimeRate * elapsedTimeRate + 0.9375f
        } else {
            elapsedTimeRate -= 2.625f / 2.75f
            7.5625f *  elapsedTimeRate * elapsedTimeRate + 0.984375f
        }
    }

    /*
     private static float getBounceOut(float elapsedTimeRate) {
        if (elapsedTimeRate < 1 / 2.75) {
            return (float) (7.5625 * elapsedTimeRate * elapsedTimeRate);
        } else if (elapsedTimeRate < 2 / 2.75) {
            return (float) (7.5625 * (elapsedTimeRate -= 1.5 / 2.75) * elapsedTimeRate + 0.75);
        } else if (elapsedTimeRate < 2.5 / 2.75) {
            return (float) (7.5625 * (elapsedTimeRate -= 2.25 / 2.75) * elapsedTimeRate + 0.9375);
        } else {
            return (float) (7.5625 * (elapsedTimeRate -= 2.625 / 2.75) * elapsedTimeRate + 0.984375);
        }
    }
     */

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amplitude Amplitude of easing
     * @param period Animation of period
     * @return easedValue
     */
    private fun getElasticIn(elapsedTimeRate: Float, amplitude: Float, period: Float): Float {
        var elapsedTimeRate = elapsedTimeRate
        if (elapsedTimeRate == 0f || elapsedTimeRate == 1f) return elapsedTimeRate
        val s = period / pi2 * asin(1 / amplitude)
        return -(amplitude * pow(2.0f, (10f * 1f.let { elapsedTimeRate -= it; elapsedTimeRate })) * sin((elapsedTimeRate - s) * pi2 / period))
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amplitude Amplitude of easing
     * @param period Animation of period
     * @return easedValue
     */
    private fun getElasticOut(elapsedTimeRate: Float, amplitude: Float, period: Float): Float {
        if (elapsedTimeRate == 0f || elapsedTimeRate == 1f) return elapsedTimeRate
        val s = period / pi2 * asin(1f / amplitude)
        return (amplitude * pow(2.0f, (-10f * elapsedTimeRate)) * sin((elapsedTimeRate - s) * pi2 / period) + 1).toFloat()
    }

    /**
     * @param elapsedTimeRate Elapsed time / Total time
     * @param amplitude Amplitude of easing
     * @param period Animation of period
     * @return easedValue
     */
    private fun getElasticInOut(elapsedTimeRate: Float, amplitude: Float, period: Float): Float {
        var elapsedTimeRate = elapsedTimeRate
        val s = period / pi2 * asin(1f / amplitude)
        return if (2.let { elapsedTimeRate *= it; elapsedTimeRate } < 1) {
            (-0.5f * (amplitude * pow(2.0f, (10f * 1f.let { elapsedTimeRate -= it; elapsedTimeRate })) * sin((elapsedTimeRate - s) * pi2 / period)))
        } else {
            elapsedTimeRate -= 1f
            multiply(
                amplitude,
                pow(2.0f, -10f * elapsedTimeRate),
                sin((elapsedTimeRate - s) * pi2 / period) * 0.5f + 1f
            )
        }
    }

}