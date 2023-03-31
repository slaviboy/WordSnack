package com.slaviboy.wordsnack.entities

import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class FontSizeRange(
    val min: TextUnit,
    val max: TextUnit,
    val step: TextUnit = DEFAULT_TEXT_STEP,
) {
    init {
        require(min < max) { "Min should be less than max, $this" }
        require(step.value > 0) { "Step should be greater than 0, $this" }
    }

    companion object {
        private val DEFAULT_TEXT_STEP = 1.sp
    }
}