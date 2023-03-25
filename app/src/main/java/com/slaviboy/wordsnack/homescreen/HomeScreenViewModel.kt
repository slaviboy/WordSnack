package com.slaviboy.wordsnack.homescreen

import android.content.res.AssetManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.asImageBitmap
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.slaviboy.wordsnack.extensions.getClipData
import com.slaviboy.wordsnack.extensions.readAsBitmap
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val gson: Gson,
    private val assetManager: AssetManager
) : ViewModel() {

    val commonClipDate by mutableStateOf(
        assetManager.getClipData(gson, "common")
    )

    val homeImageBitmap by mutableStateOf(
        assetManager.readAsBitmap("splash_background").asImageBitmap()
    )


}