package com.slaviboy.wordsnack.interpolators

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import com.slaviboy.wordsnack.core.Math.PI
import com.slaviboy.wordsnack.interpolators.Easing.Ease
import com.slaviboy.wordsnack.interpolators.Easing.EaseIn
import com.slaviboy.wordsnack.interpolators.Easing.EaseInBack
import com.slaviboy.wordsnack.interpolators.Easing.EaseInBounce
import com.slaviboy.wordsnack.interpolators.Easing.EaseInCirc
import com.slaviboy.wordsnack.interpolators.Easing.EaseInCubic
import com.slaviboy.wordsnack.interpolators.Easing.EaseInElastic
import com.slaviboy.wordsnack.interpolators.Easing.EaseInExpo
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOut
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutBack
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutBounce
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutCirc
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutCubic
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutElastic
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutExpo
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutQuad
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutQuart
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutQuint
import com.slaviboy.wordsnack.interpolators.Easing.EaseInOutSine
import com.slaviboy.wordsnack.interpolators.Easing.EaseInQuad
import com.slaviboy.wordsnack.interpolators.Easing.EaseInQuart
import com.slaviboy.wordsnack.interpolators.Easing.EaseInQuint
import com.slaviboy.wordsnack.interpolators.Easing.EaseInSine
import com.slaviboy.wordsnack.interpolators.Easing.EaseOut
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutBack
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutBounce
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutCirc
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutCubic
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutElastic
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutExpo
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutQuad
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutQuart
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutQuint
import com.slaviboy.wordsnack.interpolators.Easing.EaseOutSine
import com.slaviboy.wordsnack.interpolators.Easing.FastOutLinearIn
import com.slaviboy.wordsnack.interpolators.Easing.FastOutSlowIn
import com.slaviboy.wordsnack.interpolators.Easing.Linear
import com.slaviboy.wordsnack.interpolators.Easing.LinearOutSlowIn
import kotlin.math.absoluteValue
import kotlin.math.pow
import kotlin.math.sin

/**
 * The Easing class provides a collection of ease functions. It does not use the standard 4 param
 * ease signature. Instead it uses a single param which indicates the current linear ratio (0 to 1) of the tween.
 */
object EasingProvider {

    private const val pi2 = PI * 2f

    /**
     * @param easing Easing type
     * @param fraction Elapsed time / Total time
     * @return easedValue
     */
    operator fun get(easing: Easing, fraction: Float): Float {
        return when (easing) {
            is Ease -> ease(fraction)
            is EaseOut -> easeOut(fraction)
            is EaseIn -> easeIn(fraction)
            is EaseInOut -> easeInOut(fraction)
            is EaseInSine -> easeInSine(fraction)
            is EaseOutSine -> easeOutSine(fraction)
            is EaseInOutSine -> easeInOutSine(fraction)
            is EaseInCubic -> easeInCubic(fraction)
            is EaseOutCubic -> easeOutCubic(fraction)
            is EaseInOutCubic -> easeInOutCubic(fraction)
            is EaseInQuint -> easeInQuint(fraction)
            is EaseOutQuint -> easeOutQuint(fraction)
            is EaseInOutQuint -> easeInOutQuint(fraction)
            is EaseInCirc -> easeInCirc(fraction)
            is EaseOutCirc -> easeOutCirc(fraction)
            is EaseInOutCirc -> easeInOutCirc(fraction)
            is EaseInQuad -> easeInQuad(fraction)
            is EaseOutQuad -> easeOutQuad(fraction)
            is EaseInOutQuad -> easeInOutQuad(fraction)
            is EaseInQuart -> easeInQuart(fraction)
            is EaseOutQuart -> easeOutQuart(fraction)
            is EaseInOutQuart -> easeInOutQuart(fraction)
            is EaseInExpo -> easeInExpo(fraction)
            is EaseOutExpo -> easeOutExpo(fraction)
            is EaseInOutExpo -> easeInOutExpo(fraction)
            is EaseInBack -> easeInBack(fraction)
            is EaseOutBack -> easeOutBack(fraction)
            is EaseInOutBack -> easeInOutBack(fraction)
            is EaseInElastic -> easeInElastic(fraction)
            is EaseOutElastic -> easeOutElastic(fraction)
            is EaseInOutElastic -> easeInOutElastic(fraction)
            is EaseOutBounce -> easeOutBounce(fraction)
            is EaseInBounce -> easeInBounce(fraction)
            is EaseInOutBounce -> easeInOutBounce(fraction)
            is FastOutSlowIn -> fastOutSlowIn(fraction)
            is LinearOutSlowIn -> linearOutSlowIn(fraction)
            is FastOutLinearIn -> fastOutLinearIn(fraction)
            is Linear -> linear(fraction)
        }
    }

    /**
     * Easing Curve that speeds up quickly and ends slowly.
     *
     * ![Ease Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease.gif)
     */
    private fun ease(fraction: Float): Float {
        return cubicBezierEasing(0.25f, 0.1f, 0.25f, 1.0f, fraction)
    }

    /**
     * Easing Curve that starts quickly and ends slowly.
     *
     * ![EaseOut Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out.gif)
     */
    private fun easeOut(fraction: Float): Float {
        return cubicBezierEasing(0f, 0f, 0.58f, 1f, fraction)
    }

    /**
     * Easing Curve that starts slowly and ends quickly.
     *
     * ![EaseIn Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in.gif)
     */
    private fun easeIn(fraction: Float): Float {
        return cubicBezierEasing(0.42f, 0f, 1f, 1f, fraction)
    }

    /**
     * Easing Curve that starts slowly, speeds up and then ends slowly.
     *
     * ![EaseInOut Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out.gif)
     */
    private fun easeInOut(fraction: Float): Float {
        return cubicBezierEasing(0.42f, 0.0f, 0.58f, 1.0f, fraction)
    }

    /**
     * Easing Curve that starts slowly and ends quickly. Similar to EaseIn, but with slightly less abrupt beginning
     *
     *  ![EaseInSine Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_sine.gif)
     */
    private fun easeInSine(fraction: Float): Float {
        return cubicBezierEasing(0.12f, 0f, 0.39f, 0f, fraction)
    }

    /**
     *  ![EaseOutSine Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_sine.gif)
     */
    private fun easeOutSine(fraction: Float): Float {
        return cubicBezierEasing(0.61f, 1f, 0.88f, 1f, fraction)
    }

    /**
     *  ![EaseInOutSine Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_sine.gif)
     */
    private fun easeInOutSine(fraction: Float): Float {
        return cubicBezierEasing(0.37f, 0f, 0.63f, 1f, fraction)
    }

    /**
     *  ![EaseInCubic Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_cubic.gif)
     */
    private fun easeInCubic(fraction: Float): Float {
        return cubicBezierEasing(0.32f, 0f, 0.67f, 0f, fraction)
    }

    /**
     *  ![EaseOutCubic Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_cubic.gif)
     */
    private fun easeOutCubic(fraction: Float): Float {
        return cubicBezierEasing(0.33f, 1f, 0.68f, 1f, fraction)
    }

    /**
     *  ![EaseInOutCubic Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_cubic.gif)
     */
    private fun easeInOutCubic(fraction: Float): Float {
        return cubicBezierEasing(0.65f, 0f, 0.35f, 1f, fraction)
    }

    /**
     *  ![EaseInQuint Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_quint.gif)
     */
    private fun easeInQuint(fraction: Float): Float {
        return cubicBezierEasing(0.64f, 0f, 0.78f, 0f, fraction)
    }

    /**
     *  ![EaseOutQuint Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_quint.gif)
     */
    private fun easeOutQuint(fraction: Float): Float {
        return cubicBezierEasing(0.22f, 1f, 0.36f, 1f, fraction)
    }

    /**
     *  ![EaseInOutQuint Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_quint.gif)
     */
    private fun easeInOutQuint(fraction: Float): Float {
        return cubicBezierEasing(0.83f, 0f, 0.17f, 1f, fraction)
    }

    /**
     *  ![EaseInCirc Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_circ.gif)
     */
    private fun easeInCirc(fraction: Float): Float {
        return cubicBezierEasing(0.55f, 0f, 1f, 0.45f, fraction)
    }

    /**
     *  ![EaseOutCirc Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_circ.gif)
     */
    private fun easeOutCirc(fraction: Float): Float {
        return cubicBezierEasing(0f, 0.55f, 0.45f, 1f, fraction)
    }

    /**
     *  ![EaseInOutCirc Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_circ.gif)
     */
    private fun easeInOutCirc(fraction: Float): Float {
        return cubicBezierEasing(0.85f, 0f, 0.15f, 1f, fraction)
    }

    /**
     *  ![EaseInQuad Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_quad.gif)
     */
    private fun easeInQuad(fraction: Float): Float {
        return cubicBezierEasing(0.11f, 0f, 0.5f, 0f, fraction)
    }

    /**
     *  ![EaseOutQuad Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_quad.gif)
     */
    private fun easeOutQuad(fraction: Float): Float {
        return cubicBezierEasing(0.5f, 1f, 0.89f, 1f, fraction)
    }

    /**
     *  ![EaseInOutQuad Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_quad.gif)
     */
    private fun easeInOutQuad(fraction: Float): Float {
        return cubicBezierEasing(0.45f, 0f, 0.55f, 1f, fraction)
    }

    /**
     *  ![EaseInQuart Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_quart.gif)
     */
    private fun easeInQuart(fraction: Float): Float {
        return cubicBezierEasing(0.5f, 0f, 0.75f, 0f, fraction)
    }

    /**
     *  ![EaseOutQuart Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_quart.gif)
     */
    private fun easeOutQuart(fraction: Float): Float {
        return cubicBezierEasing(0.25f, 1f, 0.5f, 1f, fraction)
    }

    /**
     *  ![EaseInOutQuart Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_quart.gif)
     */
    private fun easeInOutQuart(fraction: Float): Float {
        return cubicBezierEasing(0.76f, 0f, 0.24f, 1f, fraction)
    }

    /**
     *  ![EaseInExpo Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_expo.gif)
     */
    private fun easeInExpo(fraction: Float): Float {
        return cubicBezierEasing(0.7f, 0f, 0.84f, 0f, fraction)
    }

    /**
     *  ![EaseOutExpo Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_expo.gif)
     */
    private fun easeOutExpo(fraction: Float): Float {
        return cubicBezierEasing(0.16f, 1f, 0.3f, 1f, fraction)
    }

    /**
     *  ![EaseInOutExpo Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_expo.gif)
     */
    private fun easeInOutExpo(fraction: Float): Float {
        return cubicBezierEasing(0.87f, 0f, 0.13f, 1f, fraction)
    }

    /**
     *  ![EaseInBack Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_back.gif)
     */
    private fun easeInBack(fraction: Float): Float {
        return cubicBezierEasing(0.36f, 0f, 0.66f, -0.56f, fraction)
    }

    /**
     *  ![EaseOutBack Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_back.gif)
     */
    private fun easeOutBack(fraction: Float): Float {
        return cubicBezierEasing(0.34f, 1.56f, 0.64f, 1f, fraction)
    }

    /**
     *  ![EaseInOutBack Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_back.gif)
     */
    private fun easeInOutBack(fraction: Float): Float {
        return cubicBezierEasing(0.68f, -0.6f, 0.32f, 1.6f, fraction)
    }

    /**
     *  ![EaseInElastic Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_elastic.gif)
     */
    private fun easeInElastic(fraction: Float): Float {
        val c4 = (2f * kotlin.math.PI) / 3f

        return when (fraction) {
            0f -> 0f
            1f -> 1f
            else ->
                (-(2.0f).pow(10f * fraction - 10.0f) *
                        sin((fraction * 10f - 10.75f) * c4)).toFloat()
        }
    }

    /**
     *  ![EaseOutElastic Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_elastic.gif)
     */
    private fun easeOutElastic(fraction: Float): Float {
        val c4 = (2f * kotlin.math.PI) / 3f

        return when (fraction) {
            0f -> 0f
            1f -> 1f
            else ->
                ((2.0f).pow(-10.0f * fraction) *
                        sin((fraction * 10f - 0.75f) * c4) + 1f).toFloat()
        }
    }

    /**
     *  ![EaseInOutElastic Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_elastic.gif)
     */
    private fun easeInOutElastic(fraction: Float): Float {
        val c5 = (2f * kotlin.math.PI) / 4.5f
        return when (fraction) {
            0f -> 0f
            1f -> 1f
            in 0f..0.5f ->
                (-(2.0f.pow(20.0f * fraction - 10.0f) *
                        sin((20.0f * fraction - 11.125f) * c5)) / 2.0f).toFloat()

            else ->
                ((2.0f.pow(-20.0f * fraction + 10.0f) *
                        sin((fraction * 20f - 11.125f) * c5)) / 2f).toFloat() + 1f
        }
    }

    /**
     *  ![EaseOutBounce Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_out_bounce.gif)
     */
    private fun easeOutBounce(fraction: Float): Float {
        val n1 = 7.5625f
        val d1 = 2.75f
        var newFraction = fraction

        return if (newFraction < 1f / d1) {
            n1 * newFraction * newFraction
        } else if (newFraction < 2f / d1) {
            newFraction -= 1.5f / d1
            n1 * newFraction * newFraction + 0.75f
        } else if (newFraction < 2.5f / d1) {
            newFraction -= 2.25f / d1
            n1 * newFraction * newFraction + 0.9375f
        } else {
            newFraction -= 2.625f / d1
            n1 * newFraction * newFraction + 0.984375f
        }
    }

    /**
     *  ![EaseInBounce Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_bounce.gif)
     */
    private fun easeInBounce(fraction: Float): Float {
        return 1 - easeOutBounce(1f - fraction)
    }

    /**
     *  ![EaseInOutBounce Curve](https://developer.android.com/images/reference/androidx/compose/animation-core/ease_in_out_bounce.gif)
     */
    private fun easeInOutBounce(fraction: Float): Float {
        return if (fraction < 0.5) {
            (1 - easeOutBounce(1f - 2f * fraction)) / 2f
        } else {
            (1 + easeOutBounce(2f * fraction - 1f)) / 2f
        }
    }

    /**
     * Elements that begin and end at rest use this standard easing. They speed up quickly
     * and slow down gradually, in order to emphasize the end of the transition.
     *
     * Standard easing puts subtle attention at the end of an animation, by giving more
     * time to deceleration than acceleration. It is the most common form of easing.
     *
     * This is equivalent to the Android `FastOutSlowInInterpolator`
     */
    private fun fastOutSlowIn(fraction: Float): Float {
        return cubicBezierEasing(0.4f, 0.0f, 0.2f, 1.0f, fraction)
    }

    /**
     * Incoming elements are animated using deceleration easing, which starts a transition
     * at peak velocity (the fastest point of an element’s movement) and ends at rest.
     *
     * This is equivalent to the Android `LinearOutSlowInInterpolator`
     */
    private fun linearOutSlowIn(fraction: Float): Float {
        return cubicBezierEasing(0.0f, 0.0f, 0.2f, 1.0f, fraction)
    }

    /**
     * Elements exiting a screen use acceleration easing, where they start at rest and
     * end at peak velocity.
     *
     * This is equivalent to the Android `FastOutLinearInInterpolator`
     */
    private fun fastOutLinearIn(fraction: Float): Float {
        return cubicBezierEasing(0.4f, 0.0f, 1.0f, 1.0f, fraction)
    }

    /**
     * It returns fraction unmodified. This is useful as a default value for
     * cases where a [Easing] is required but no actual easing is desired.
     */
    private fun linear(fraction: Float): Float {
        return fraction
    }

    /**
     * A cubic polynomial easing.
     *
     * The [CubicBezierEasing] class implements third-order Bézier curves.
     *
     * This is equivalent to the Android `PathInterpolator`
     *
     * Rather than creating a new instance, consider using one of the common
     * cubic [Easing]s:
     *
     * @see FastOutSlowInEasing
     * @see LinearOutSlowInEasing
     * @see FastOutLinearInEasing
     *
     * @param a The x coordinate of the first control point.
     *          The line through the point (0, 0) and the first control point is tangent
     *          to the easing at the point (0, 0).
     * @param b The y coordinate of the first control point.
     *          The line through the point (0, 0) and the first control point is tangent
     *          to the easing at the point (0, 0).
     * @param c The x coordinate of the second control point.
     *          The line through the point (1, 1) and the second control point is tangent
     *          to the easing at the point (1, 1).
     * @param d The y coordinate of the second control point.
     *          The line through the point (1, 1) and the second control point is tangent
     *          to the easing at the point (1, 1).
     */
    private fun cubicBezierEasing(
        a: Float,
        b: Float,
        c: Float,
        d: Float,
        fraction: Float
    ): Float {
        if (fraction > 0f && fraction < 1f) {
            var start = 0.0f
            var end = 1.0f
            while (true) {
                val midpoint = (start + end) / 2
                val estimate = evaluateCubic(a, c, midpoint)
                if ((fraction - estimate).absoluteValue < CubicErrorBound)
                    return evaluateCubic(b, d, midpoint)
                if (estimate < fraction)
                    start = midpoint
                else
                    end = midpoint
            }
        } else {
            return fraction
        }
    }

    private fun evaluateCubic(a: Float, b: Float, m: Float): Float {
        return 3 * a * (1 - m) * (1 - m) * (m) +
                3 * b * (1 - m) * (m * m) +
                (m * m * m)
    }

    private const val CubicErrorBound: Float = 0.001f
}