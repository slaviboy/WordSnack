package com.slaviboy.wordsnack.gamescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.sw
import com.slaviboy.wordsnack.R
import com.slaviboy.wordsnack.composables.AutoResizeText
import com.slaviboy.wordsnack.composables.ClippedButton
import com.slaviboy.wordsnack.composables.ClippedImage
import com.slaviboy.wordsnack.destinations.GameScreenComposableDestination
import com.slaviboy.wordsnack.entities.ClipButtonState
import com.slaviboy.wordsnack.entities.CommonImageType
import com.slaviboy.wordsnack.entities.FontSizeRange
import com.slaviboy.wordsnack.ui.theme.ButtonTextStyle

@Destination
@Composable
fun GameScreenComposable(
    navigator: DestinationsNavigator,
    viewModel: GameScreenViewModel
) {
    Image(
        bitmap = viewModel.backgroundImageBitmap,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    Box(
        contentAlignment = Alignment.TopStart,
        modifier = Modifier
            .fillMaxSize()
    ) {

        TopBarComposable(
            viewModel = viewModel,
            boxScope = this
        )

        BottomBarComposable(
            viewModel = viewModel,
            boxScope = this
        )

        ClippedImage(
            width = 1.dw,
            clipData = viewModel.commonClipData,
            imageType = CommonImageType.WordlistBg,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .offset(y = 0.07.dh)
        )

        ClippedButton(
            offsetX = (-0.005).dw,
            offsetY = (0.05).dw,
            width = 0.16.dw,
            iconWidth = 0.11.dw,
            iconOffsetY = (-0.01).dw,
            clipButtonState = ClipButtonState.ShareButtonState,
            clipData = viewModel.commonClipData,
            modifier = Modifier.align(Alignment.CenterEnd),
            text = stringResource(id = R.string.game_ask_friends).uppercase(),
            textOffsetY = (0.09).dw,
            textSize = 0.03.sw
        ) {

        }
    }
}

@Composable
fun TopBarComposable(
    viewModel: GameScreenViewModel,
    boxScope: BoxScope
) = with(boxScope) {
    ClippedImage(
        width = 1.dw,
        clipData = viewModel.commonClipData,
        imageType = CommonImageType.StatusbarBg,
        modifier = Modifier.align(Alignment.TopCenter)
    )

    ClippedButton(
        offsetX = (0.021).dw,
        offsetY = (0.015).dw,
        width = 0.09.dw,
        iconWidth = 0.11.dw,
        iconOffsetY = (-0.01).dw,
        clipButtonState = ClipButtonState.SettingsButtonState,
        clipData = viewModel.commonClipData,
        modifier = Modifier.align(Alignment.TopStart)
    )

    Box(
        modifier = Modifier
            .align(Alignment.TopStart)
            .width(0.2.dw)
            .height(0.06.dw)
            .offset(0.115.dw, 0.030.dw),
        contentAlignment = Alignment.Center
    ) {
        AutoResizeText(
            text = stringResource(id = R.string.dialog_settings_title).uppercase(),
            textAlign = TextAlign.Center,
            style = ButtonTextStyle,
            color = Color(0xFFe8dfbe),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
            fontSizeRange = FontSizeRange(
                min = 0.01.sw,
                max = 0.04.sw
            ),
            overflow = TextOverflow.Ellipsis
        )
    }

    ClippedButton(
        offsetX = (-0.021).dw,
        offsetY = (0.015).dw,
        width = 0.09.dw,
        iconWidth = 0.11.dw,
        iconOffsetY = (-0.01).dw,
        clipButtonState = ClipButtonState.ShopButtonState,
        clipData = viewModel.commonClipData,
        modifier = Modifier.align(Alignment.TopEnd)
    )

    ClippedImage(
        offsetX = (-0.265).dw,
        offsetY = (0.01).dw,
        width = 0.09.dw,
        clipData = viewModel.commonClipData,
        imageType = CommonImageType.CreditsIcon,
        modifier = Modifier.align(Alignment.TopEnd)
    )

    Box(
        modifier = Modifier
            .align(Alignment.TopEnd)
            .width(0.15.dw)
            .height(0.06.dw)
            .offset((-0.115).dw, 0.030.dw),
        contentAlignment = Alignment.Center
    ) {
        AutoResizeText(
            text = "34",
            textAlign = TextAlign.Center,
            style = ButtonTextStyle,
            color = Color(0xFFe8dfbe),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
            fontSizeRange = FontSizeRange(
                min = 0.003.sw,
                max = 0.04.sw
            ),
            overflow = TextOverflow.Ellipsis
        )
    }

    Box(
        modifier = Modifier
            .align(Alignment.TopCenter)
            .width(0.2.dw)
            .height(0.045.dw)
            .offset(0.dw, 0.039.dw),
        contentAlignment = Alignment.Center
    ) {
        AutoResizeText(
            text = stringResource(id = R.string.status_level).format(3).uppercase(),
            textAlign = TextAlign.Center,
            style = ButtonTextStyle,
            color = Color(0xFFe8dfbe),
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth(),
            fontSizeRange = FontSizeRange(
                min = 0.01.sw,
                max = 0.04.sw
            ),
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun BottomBarComposable(
    viewModel: GameScreenViewModel,
    boxScope: BoxScope
) = with(boxScope) {
    ClippedImage(
        width = 1.dw,
        clipData = viewModel.commonClipData,
        imageType = CommonImageType.FooterBg,
        modifier = Modifier.align(Alignment.BottomCenter)
    )

    ClippedButton(
        offsetX = (0.005).dw,
        offsetY = (-0.052).dw,
        width = 0.23.dw,
        iconWidth = 0.11.dw,
        iconOffsetY = (-0.01).dw,
        clipButtonState = ClipButtonState.HintButtonState,
        clipData = viewModel.commonClipData,
        modifier = Modifier.align(Alignment.BottomStart)
    ) {

    }

    ClippedButton(
        offsetX = (-0.005).dw,
        offsetY = (-0.052).dw,
        width = 0.23.dw,
        iconWidth = 0.11.dw,
        iconOffsetY = (-0.01).dw,
        clipButtonState = ClipButtonState.ShuffleButtonState,
        clipData = viewModel.commonClipData,
        modifier = Modifier.align(Alignment.BottomEnd)
    ) {

    }
}