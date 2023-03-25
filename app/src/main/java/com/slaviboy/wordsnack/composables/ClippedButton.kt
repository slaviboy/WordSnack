package com.slaviboy.wordsnack.composables

import android.view.MotionEvent
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.entities.ClipButtonState
import com.slaviboy.wordsnack.entities.ClipData
import com.slaviboy.wordsnack.ui.theme.ButtonTextStyle

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ClippedButton(
    text: String,
    width: Dp,
    clipData: ClipData,
    clipButtonState: ClipButtonState,
    modifier: Modifier = Modifier,
    background: Color = Color.Transparent,
    offsetX: Dp = 0.dw,
    offsetY: Dp = 0.dw,
    textOffsetX: Dp = 0.dw,
    textOffsetY: Dp = 0.dw,
    textSize: TextUnit = TextUnit(width.value * 0.21f, type = TextUnitType.Sp),
    textColor: Color = Color.White,
    textShadowColor: Color = Color(0xC1000000),
    textShadowOffsetX: Dp = 0.dw,
    textShadowOffsetY: Dp = width * -0.018f,
    onClick: () -> Unit = {}
) {
    var backgroundImageType by remember { mutableStateOf(clipButtonState.up) }
    var iconImageType by remember { mutableStateOf(clipButtonState.iconUp) }
    Box(
        modifier = modifier
            .wrapContentSize()
            .offset(offsetX, offsetY)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        ClippedImage(
            width = width,
            modifier = modifier
                .pointerInteropFilter {
                    when (it.action) {
                        MotionEvent.ACTION_DOWN -> {
                            backgroundImageType = clipButtonState.down
                            clipButtonState.iconDown?.let {
                                iconImageType = it
                            }
                        }

                        MotionEvent.ACTION_UP -> {
                            backgroundImageType = clipButtonState.up
                            clipButtonState.iconUp?.let {
                                iconImageType = it
                            }
                            onClick.invoke()
                        }
                    }
                    true
                },
            clipData = clipData,
            imageType = backgroundImageType
        )
        iconImageType?.let {
            ClippedImage(
                width = width,
                clipData = clipData,
                imageType = it
            )
        }
        Text(
            text = text,
            style = ButtonTextStyle,
            fontSize = textSize,
            color = textShadowColor,
            modifier = Modifier
                .offset(textOffsetX, textOffsetY)
        )
        Text(
            text = text,
            style = ButtonTextStyle,
            fontSize = textSize,
            color = textColor,
            modifier = Modifier
                .offset(
                    x = textOffsetX + textShadowOffsetX,
                    y = textOffsetY + textShadowOffsetY
                )
        )
    }
}