package com.slaviboy.wordsnack.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.slaviboy.wordsnack.entities.ClipData
import com.slaviboy.wordsnack.entities.ImageType
import com.slaviboy.wordsnack.extensions.clipAsImageBitmap

@Composable
fun ClippedImage(
    width: Dp,
    modifier: Modifier,
    clipData: ClipData,
    imageType: ImageType
) {
    val bitmap = clipData.bitmap
    val clipConfig = clipData.clipConfigs[imageType.index]
    Image(
        bitmap = bitmap.clipAsImageBitmap(clipConfig),
        contentDescription = null,
        modifier = modifier
            .width(width)
            .height(width * clipConfig.heightToWidth)
    )
}