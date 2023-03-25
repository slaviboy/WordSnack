package com.slaviboy.wordsnack.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.slaviboy.wordsnack.entities.ClipData
import com.slaviboy.wordsnack.entities.CommonImageType

@Composable
fun AppleIcon(
    width: Dp,
    modifier: Modifier = Modifier,
    commonClipData: ClipData
) = ClippedImage(
    width = width,
    modifier = modifier,
    clipData = commonClipData,
    imageType = CommonImageType.AppleIcon
)