package com.slaviboy.wordsnack.gamescreen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInteropFilter
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
import com.slaviboy.wordsnack.composables.Letter
import com.slaviboy.wordsnack.destinations.HomeScreenComposableDestination
import com.slaviboy.wordsnack.entities.ClipButtonState
import com.slaviboy.wordsnack.entities.CommonImageType
import com.slaviboy.wordsnack.entities.FontSizeRange
import com.slaviboy.wordsnack.ui.theme.ButtonTextStyle

@Destination(start = true)
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

        WordsComposable(
            viewModel = viewModel,
            boxScope = this
        )

        AllowedLettersComposable(
            viewModel = viewModel,
            boxScope = this
        )

        BottomBarComposable(
            viewModel = viewModel,
            boxScope = this
        )

        ClippedButton(
            offsetX = (-0.04).dw,
            offsetY = (0.05).dw,
            width = 0.16.dw,
            iconWidth = 0.11.dw,
            iconOffsetY = (-0.01).dw,
            clipButtonState = ClipButtonState.ShareButtonState,
            clipData = viewModel.commonClipData,
            modifier = Modifier.align(Alignment.CenterEnd),
            text = stringResource(id = R.string.game_ask_friends)
                .uppercase()
                .replace(' ', '\n'),
            textOffsetY = (0.09).dw,
            textSize = 0.03.sw
        ) {
            navigator.navigate(HomeScreenComposableDestination)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AllowedLettersComposable(
    viewModel: GameScreenViewModel,
    boxScope: BoxScope
) = with(boxScope) {

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(viewModel.allowedLettersBoxWidth)
            .height(viewModel.allowedLettersBoxHeight)
            .offset(y = (-0.2).dw)
            .align(Alignment.BottomCenter)
            .pointerInteropFilter {
                viewModel.onMotionEvent(it)
                true
            }
        //.background(viewModel.bg)
    ) {
        viewModel.allowedLetters.forEachIndexed { i, char ->
            Letter(
                text = char.toString().uppercase(),
                clipData = viewModel.commonClipData,
                imageType = CommonImageType.LetterLarge,
                shadowImageType = CommonImageType.LetterShadow,
                width = viewModel.allowedLettersWidth,
                modifier = Modifier
                    .offset(
                        x = viewModel.allowedLettersPosition[i].x,
                        y = viewModel.allowedLettersPosition[i].y
                    )
                    .rotate(viewModel.allowedLettersAngles[i])
            )
        }

        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val width = size.width
            drawPath(
                path = viewModel.passThroughPath,
                color = Color(0xCDFFFFFF),
                style = Stroke(
                    width = width * 0.028f,
                    cap = StrokeCap.Round,
                    join = StrokeJoin.Round
                )
            )
        }
    }

    SelectedLetters(
        viewModel = viewModel,
        boxScope = this
    )
}

@Composable
fun SelectedLetters(
    viewModel: GameScreenViewModel,
    boxScope: BoxScope
) = with(boxScope) {
    if (viewModel.passThroughSelectedLetters.isEmpty()) return@with
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .wrapContentHeight()
            .width(viewModel.passThroughSelectedLettersBoxWidth)
            .align(Alignment.Center)
            .offset(y = (-0.13).dw)
    ) {
        if (viewModel.passThroughSelectedLetters.size > 2) {
            ClippedImage(
                height = 0.2.dw,
                width = (viewModel.passThroughSelectedLettersBoxWidth - 0.1.dw),
                clipData = viewModel.commonClipData,
                imageType = CommonImageType.CurrentLettersCenter,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }

        ClippedImage(
            height = 0.2.dw,
            clipData = viewModel.commonClipData,
            imageType = CommonImageType.CurrentLettersLeft,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .align(Alignment.CenterStart)
        )

        ClippedImage(
            height = 0.2.dw,
            clipData = viewModel.commonClipData,
            imageType = CommonImageType.CurrentLettersRight,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .align(Alignment.CenterEnd)
        )
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .wrapContentSize()
            .align(Alignment.Center)
            .offset(x = (-0.01).dw, y = (-0.14).dw)
    ) {
        viewModel.passThroughSelectedLetters.forEach {
            Letter(
                text = it.toString().uppercase(),
                clipData = viewModel.commonClipData,
                imageType = CommonImageType.LetterFixed,
                width = viewModel.passThroughSelectedLetterWidth
            )
        }
    }
}

@Composable
fun WordsComposable(
    viewModel: GameScreenViewModel,
    boxScope: BoxScope
) = with(boxScope) {

    ClippedImage(
        width = 1.dw,
        clipData = viewModel.commonClipData,
        imageType = CommonImageType.WordlistBg,
        modifier = Modifier
            .align(Alignment.TopCenter)
            .offset(y = 0.07.dh)
    )

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .width(0.8.dw)
            .height(0.55.dw)
            .align(Alignment.TopCenter)
            .offset(y = 0.1.dh)
    ) {
        viewModel.words.forEach { word ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                word.forEach { _ ->
                    ClippedImage(
                        width = viewModel.letterBoxWidth,
                        clipData = viewModel.commonClipData,
                        imageType = CommonImageType.LetterBox,
                        modifier = Modifier
                            .padding(
                                horizontal = viewModel.letterBoxPaddingHorizontal,
                                vertical = viewModel.letterBoxPaddingVertical
                            )
                    )
                }
            }
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