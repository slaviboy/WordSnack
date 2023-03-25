package com.slaviboy.wordsnack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.slaviboy.composeunits.initSize
import com.slaviboy.wordsnack.destinations.HomeScreenComposableDestination
import com.slaviboy.wordsnack.extensions.transparentStatusBar
import com.slaviboy.wordsnack.homescreen.HomeScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeScreenViewModel: HomeScreenViewModel by viewModels()

    val supportedLanguages = listOf(
        "EN", "FR", "IT", "DE", "ES", "PT",
        "NL", "DA", "FI", "IS", "NO", "SV", "EL",
        "CS", "SK", "HU", "PL", "RO", "BG", "SL", "TR", "RU",
        "BS", "HR", "SR", "ET", "LT", "LV", "IN", "BE", "UK"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        initSize()

        setContent {
            DestinationsNavHost(
                dependenciesContainerBuilder = {
                    dependency(HomeScreenComposableDestination) { homeScreenViewModel }
                },
                navGraph = NavGraphs.root
            )
        }
    }
}





