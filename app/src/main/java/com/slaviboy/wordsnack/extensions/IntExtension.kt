package com.slaviboy.wordsnack.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

val Dp.textSp: TextUnit
    @Composable get() {
        return with(LocalDensity.current) {
            this@textSp.toSp()
        }
    }