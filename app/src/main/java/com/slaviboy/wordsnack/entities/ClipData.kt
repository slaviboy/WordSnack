package com.slaviboy.wordsnack.entities

import android.graphics.Bitmap

data class ClipData(
    val bitmap: Bitmap,
    val clipConfigs: List<ClipConfig>
)