package com.slaviboy.wordsnack.core

fun allTrue(vararg booleans: Boolean): Boolean {
    return booleans.all { it }
}

fun distanceBetweenTwoPoints(x1: Float, y1: Float, x2: Float, y2: Float): Float {
    val a = x1 - x2
    val b = y1 - y2
    return Math.sqrt(a * a + b * b)
}