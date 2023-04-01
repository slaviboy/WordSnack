package com.slaviboy.wordsnack.core


object Math {

    fun sqrt(a: Float): Float {
        return kotlin.math.sqrt(a.toDouble()).toFloat()
    }

    fun min(a: Float, b: Float): Float {
        return kotlin.math.min(a, b)
    }
}