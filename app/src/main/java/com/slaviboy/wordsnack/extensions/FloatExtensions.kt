package com.slaviboy.wordsnack.extensions

import androidx.compose.ui.unit.Dp

operator fun Float.times(other: Dp): Dp {
    return Dp(value = this * other.value)
}

operator fun Float.div(other: Dp): Dp {
    return Dp(value = this / other.value)
}