package com.slaviboy.wordsnack.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import com.slaviboy.composeunits.dw
import com.slaviboy.wordsnack.entities.ClipData
import com.slaviboy.wordsnack.entities.CommonImageType
import com.slaviboy.wordsnack.entities.ImageType
import com.slaviboy.wordsnack.ui.theme.LetterTextStyle

@Composable
fun Letter(
    text: String,
    clipData: ClipData,
    imageType: ImageType,
    modifier: Modifier = Modifier,
    width: Dp = 0.2.dw,
    textShadowWidth: Dp = width * 1.45f,
    textOffsetX: Dp = 0.dw,
    textOffsetY: Dp = width * -0.04f,
    textSize: TextUnit = TextUnit(width.value * 0.65f, type = TextUnitType.Sp),
    textShadowOffsetX: Dp = 0.dw,
    textShadowOffsetY: Dp = width * -0.018f,
    shadowImageType: ImageType? = null
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .wrapContentSize()
    ) {

        shadowImageType?.let {
            ClippedImage(
                width = textShadowWidth,
                clipData = clipData,
                imageType = it
            )
        }

        ClippedImage(
            width = width,
            clipData = clipData,
            imageType = imageType
        )

        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = LetterTextStyle,
            fontSize = textSize,
            color = Color(0xFFe2b36b),
            modifier = Modifier
                .width(width)
                .offset(textOffsetX, textOffsetY)
        )

        Text(
            text = text,
            textAlign = TextAlign.Center,
            style = LetterTextStyle,
            fontSize = textSize,
            color = Color(0xFF301504),
            modifier = Modifier
                .width(width)
                .offset(
                    x = textOffsetX + textShadowOffsetX,
                    y = textOffsetY + textShadowOffsetY
                )
        )
    }
}