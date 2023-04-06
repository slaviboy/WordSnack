package com.slaviboy.wordsnack.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.entities.ClipData
import com.slaviboy.wordsnack.entities.ImageType
import com.slaviboy.wordsnack.extensions.clipAsImageBitmap

@Composable
fun ClippedImage(
    clipData: ClipData,
    imageType: ImageType,
    modifier: Modifier = Modifier,
    width: Dp? = null,
    height: Dp? = null,
    offsetX: Dp = 0.dw,
    offsetY: Dp = 0.dw,
    contentScale: ContentScale = ContentScale.Fit
) {
    val bitmap = clipData.bitmap
    val clipConfig = clipData.clipConfigs[imageType.index]
    val widthDp: Dp
    val heightDp: Dp
    if (width != null && height != null) {
        widthDp = width
        heightDp = height
    } else if (width != null) {
        widthDp = width
        heightDp = (width * clipConfig.heightToWidth)
    } else if (height != null) {
        widthDp = (height * clipConfig.widthToHeight)
        heightDp = height
    } else {
        throw Exception("Width or height need to be set up.")
    }
    Image(
        bitmap = bitmap.clipAsImageBitmap(clipConfig),
        contentDescription = null,
        contentScale = contentScale,
        modifier = modifier
            .width(widthDp)
            .height(heightDp)
            .offset(offsetX, offsetY)
    )
}