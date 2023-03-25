package com.slaviboy.wordsnack.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.entities.ClipData
import com.slaviboy.wordsnack.entities.CommonImageType

@Composable
fun BlueButton(
    width: Dp,
    modifier: Modifier = Modifier,
    commonClipData: ClipData
) = ClippedButton(
    text = "Play",
    width = width,
    modifier = modifier,
    clipData = commonClipData,
    upBackgroundImageType = CommonImageType.GreenButtonUp,
    downBackgroundImageType = CommonImageType.GreenButtonDown,
    textShadowOffsetY = (-0.005).dw
)