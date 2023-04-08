package com.slaviboy.wordsnack.core

import com.slaviboy.wordsnack.core.Math.sqrt

fun allTrue(vararg booleans: Boolean): Boolean {
    return booleans.all { it }
}

fun anyTrue(vararg booleans: Boolean): Boolean {
    return booleans.any { it }
}

fun multiply(vararg floats: Float): Float {
    var mul = 1f
    floats.forEach {
        mul *= it
    }
    return mul
}

fun sum(vararg floats: Float): Float {
    var sum = 0f
    floats.forEach {
        sum += it
    }
    return sum
}

fun distanceBetweenTwoPoints(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    val a = x1 - x2
    val b = y1 - y2
    return sqrt(a * a + b * b)
}