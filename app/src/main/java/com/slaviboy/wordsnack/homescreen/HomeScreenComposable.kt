package com.slaviboy.wordsnack.homescreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.sw
import com.slaviboy.wordsnack.R
import com.slaviboy.wordsnack.composables.ClippedButton
import com.slaviboy.wordsnack.destinations.GameScreenDestination
import com.slaviboy.wordsnack.entities.ClipButtonState

@Destination(start = true)
@Composable
fun HomeScreenComposable(
    navigator: DestinationsNavigator,
    viewModel: HomeScreenViewModel
) {
    val density = LocalDensity.current
    val animVisibleState = remember { MutableTransitionState(false).apply { targetState = true } }

    Image(
        bitmap = viewModel.homeImageBitmap,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxSize()
    )

    AnimatedVisibility(
        visibleState = animVisibleState,
        enter = fadeIn(initialAlpha = 0.0f, animationSpec = tween(durationMillis = 600))
    ) {
        Box(
            contentAlignment = Alignment.TopStart,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                bitmap = viewModel.logoImageBitmap,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .width(0.85.dw)
                    .align(Alignment.TopCenter)
                    .offset(y = 0.35.dh)
            )
        }
    }

    AnimatedVisibility(
        modifier = Modifier
            .fillMaxSize(),
        visibleState = animVisibleState,
        enter = slideInVertically { with(density) { 0.5.dw.roundToPx() } } +
                fadeIn(initialAlpha = 0.0f, animationSpec = tween(durationMillis = 1000))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ClippedButton(
                text = stringResource(id = R.string.menu_button_play),
                offsetY = (-0.24).dh,
                width = 0.45.dw,
                clipButtonState = ClipButtonState.GreenButtonState,
                clipData = viewModel.commonClipData,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                navigator.navigate(GameScreenDestination)
            }
        }
    }

    AnimatedVisibility(
        visibleState = animVisibleState,
        enter = slideInVertically { with(density) { 1.5.dw.roundToPx() } } +
                fadeIn(initialAlpha = 0.0f, animationSpec = tween(durationMillis = 1300))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ClippedButton(
                text = stringResource(id = R.string.menu_button_login),
                textSize = 0.065.sw,
                textOffsetX = (-0.044).dw,
                offsetY = (-0.13).dh,
                width = 0.45.dw,
                clipButtonState = ClipButtonState.FacebookButtonState,
                clipData = viewModel.commonClipData,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {

            }

        }
    }
}