package com.slaviboy.wordsnack.interpolators

/**
 * The Easing class provides a collection of ease functions. It does not use the standard 4 param
 * ease signature. Instead it uses a single param which indicates the current linear ratio (0 to 1) of the tween.
 */
sealed class Ease {
    object Linear : Ease()
    object QuadIn : Ease()
    object QuadOut : Ease()
    object QuadInOut : Ease()
    object CubicIn : Ease()
    object CubicOut : Ease()
    object CubicInOut : Ease()
    object QuartIn : Ease()
    object QuartOut : Ease()
    object QuartInOut : Ease()
    object QuintIn : Ease()
    object QuintOut : Ease()
    object QuintInOut : Ease()
    object SineIn : Ease()
    object SineOut : Ease()
    object SineInOut : Ease()
    object BackIn : Ease()
    object BackOut : Ease()
    object BackInOut : Ease()
    object CircIn : Ease()
    object CircOut : Ease()
    object CircInOut : Ease()
    object BounceIn : Ease()
    object BounceOut : Ease()
    object BounceInOut : Ease()
    object ElasticIn : Ease()
    object ElasticOut : Ease()
    object ElasticInOut : Ease()
    object EaseInExpo : Ease()
    object EaseOutExpo : Ease()
    object EaseInOutExpo : Ease()
}