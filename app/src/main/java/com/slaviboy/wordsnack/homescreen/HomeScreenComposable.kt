package com.slaviboy.wordsnack.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.slaviboy.composeunits.dh
import com.slaviboy.composeunits.dw
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
    Image(
        bitmap = viewModel.homeImageBitmap,
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
        Image(
            bitmap = viewModel.logoImageBitmap,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .width(0.8.dw)
                .align(Alignment.TopCenter)
                .offset(y = 0.35.dh)
        )
        ClippedButton(
            text = stringResource(id = R.string.menu_button_play),
            offsetY = (-0.2).dh,
            width = 0.4.dw,
            clipButtonState = ClipButtonState.GreenButtonState,
            clipData = viewModel.commonClipData,
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            navigator.navigate(GameScreenDestination)
        }
    }
}