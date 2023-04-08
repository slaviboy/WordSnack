package com.slaviboy.wordsnack.extensions

import androidx.compose.ui.unit.Dp
import com.slaviboy.wordsnack.core.Math

operator fun Dp.times(other: Dp): Dp {
    return Dp(value = this.value * other.value)
}

fun Dp.sqrt(): Dp {
    return Dp(value = Math.sqrt(this.value))
}