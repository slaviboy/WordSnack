package com.slaviboy.wordsnack.core

import java.lang.Math


object Math {

    const val PI = kotlin.math.PI.toFloat()

    fun sqrt(a: Float): Float {
        return Math.sqrt(a.toDouble()).toFloat()
    }

    fun min(a: Float, b: Float): Float {
        return Math.min(a, b)
    }

    fun max(a: Float, b: Float): Float {
        return Math.max(a, b)
    }

    fun cos(a: Float): Float {
        return Math.cos(a.toDouble()).toFloat()
    }

    fun sin(a: Float): Float {
        return Math.sin(a.toDouble()).toFloat()
    }

    fun pow(a: Float, b: Float): Float {
        return Math.pow(a.toDouble(), b.toDouble()).toFloat()
    }

    fun abs(a: Float): Float {
        return Math.abs(a)
    }

    fun asin(a: Float): Float {
        return Math.asin(a.toDouble()).toFloat()
    }
}