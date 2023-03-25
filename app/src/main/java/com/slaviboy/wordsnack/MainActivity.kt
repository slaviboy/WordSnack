package com.slaviboy.wordsnack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.google.gson.Gson
import com.slaviboy.composeunits.dw
import com.slaviboy.composeunits.initSize
import com.slaviboy.wordsnack.composables.BlueButton
import com.slaviboy.wordsnack.extensions.getClipData
import dagger.hilt.android.AndroidEntryPoint
import java.text.MessageFormat
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var gson: Gson

    val supportedLanguages = listOf(
        "EN", "FR", "IT", "DE", "ES", "PT",
        "NL", "DA", "FI", "IS", "NO", "SV", "EL",
        "CS", "SK", "HU", "PL", "RO", "BG", "SL", "TR", "RU",
        "BS", "HR", "SR", "ET", "LT", "LV", "IN", "BE", "UK"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSize()

        val commonClipData = getClipData(gson, "common")

        setContent {
            Image(
                painter = painterResource(id = R.drawable.background),
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
                BlueButton(
                    modifier = Modifier.background(Color.Blue),
                    width = 0.4.dw,
                    commonClipData = commonClipData
                )
            }
        }
    }


}





