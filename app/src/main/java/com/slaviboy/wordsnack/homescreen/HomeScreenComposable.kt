package com.slaviboy.wordsnack.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

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
        /*BlueButton(
            modifier = Modifier.background(Color.Blue),
            width = 0.4.dw,
            commonClipData = commonClipData
        )*/
    }
    //navigator.navigate(GameScreenDestination)
}