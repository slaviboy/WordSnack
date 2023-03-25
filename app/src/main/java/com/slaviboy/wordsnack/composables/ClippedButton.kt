package com.slaviboy.wordsnack.composables

import android.view.MotionEvent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.unit.Dp
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.entities.ClipData
import com.slaviboy.wordsnack.entities.ImageType
import com.slaviboy.wordsnack.ui.theme.ButtonTextStyle

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClippedButton(
    text: String,
    width: Dp,
    modifier: Modifier = Modifier,
    clipData: ClipData,
    upBackgroundImageType: ImageType,
    downBackgroundImageType: ImageType,
    upIconImageType: ImageType? = null,
    downIconImageType: ImageType? = null,
    textColor: Color = Color.White,
    textShadowColor: Color = Color(0xC1000000),
    textShadowOffsetX: Dp = 0.dw,
    textShadowOffsetY: Dp = 0.dw
) {
    var backgroundImageType by remember { mutableStateOf(upBackgroundImageType) }
    Box(
        modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center
    ) {
        ClippedImage(
            width = width,
            modifier = modifier.pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> backgroundImageType = downBackgroundImageType
                    MotionEvent.ACTION_UP -> backgroundImageType = upBackgroundImageType
                }
                true
            },
            clipData = clipData,
            imageType = backgroundImageType
        )
        upIconImageType?.let {
            ClippedImage(
                width = width,
                modifier = modifier,
                clipData = clipData,
                imageType = it
            )
        }
        Text(
            text = text,
            style = ButtonTextStyle,
            color = textShadowColor
        )
        Text(
            text = text,
            style = ButtonTextStyle,
            color = textColor,
            modifier = Modifier.offset(x = textShadowOffsetX, y = textShadowOffsetY)
        )
    }
}