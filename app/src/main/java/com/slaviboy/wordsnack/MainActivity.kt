package com.slaviboy.wordsnack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.dependency
import com.slaviboy.composeunits.initSize
import com.slaviboy.wordsnack.destinations.GameScreenComposableDestination
import com.slaviboy.wordsnack.destinations.HomeScreenComposableDestination
import com.slaviboy.wordsnack.extensions.getCurrentLocale
import com.slaviboy.wordsnack.extensions.transparentStatusBar
import com.slaviboy.wordsnack.gamescreen.GameScreenViewModel
import com.slaviboy.wordsnack.homescreen.HomeScreenViewModel
import com.slaviboy.wordsnack.preferences.ApplicationPreferences
import dagger.hilt.android.AndroidEntryPoint
import java.util.Locale
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var applicationPreferences: ApplicationPreferences

    private val homeScreenViewModel: HomeScreenViewModel by viewModels()
    private val gameScreenViewModel: GameScreenViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        initSize()

        applicationPreferences.setLocale(getCurrentLocale())

        setContent {
            DestinationsNavHost(
                dependenciesContainerBuilder = {
                    dependency(HomeScreenComposableDestination) { homeScreenViewModel }
                    dependency(GameScreenComposableDestination) { gameScreenViewModel }
                },
                navGraph = NavGraphs.root
            )
        }

        val locale = Locale("bg")
        gameScreenViewModel.changeLanguage(locale)
        gameScreenViewModel.generateRandomWords()
    }
}





