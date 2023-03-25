package com.slaviboy.wordsnack.gamescreen

import android.content.res.AssetManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.slaviboy.wordsnack.extensions.readAsImageBitmap
import com.slaviboy.wordsnack.preferences.ApplicationPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GameScreenViewModel @Inject constructor(
    private val gson: Gson,
    private val assetManager: AssetManager,
    private val applicationPreferences: ApplicationPreferences
) : ViewModel() {

    val backgroundImageBitmap by mutableStateOf(
        assetManager.readAsImageBitmap("background_game")
    )
}