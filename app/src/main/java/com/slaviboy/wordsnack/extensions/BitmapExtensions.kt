package com.slaviboy.wordsnack.extensions

import android.graphics.Bitmap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import com.slaviboy.wordsnack.entities.ClipConfig

fun Bitmap.clipAsImageBitmap(
    clipConfig: ClipConfig
): ImageBitmap {
    return Bitmap.createBitmap(
        this,
        clipConfig.x,
        clipConfig.y,
        clipConfig.width,
        clipConfig.height
    ).asImageBitmap()
}