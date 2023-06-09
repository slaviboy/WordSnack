package com.slaviboy.wordsnack.homescreen

import android.content.res.AssetManager
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.ImageBitmap
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.slaviboy.wordsnack.entities.Language
import com.slaviboy.wordsnack.extensions.readAsClipData
import com.slaviboy.wordsnack.extensions.readAsImageBitmap
import com.slaviboy.wordsnack.preferences.ApplicationPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val gson: Gson,
    private val assetManager: AssetManager,
    private val applicationPreferences: ApplicationPreferences
) : ViewModel() {

    private val logoSupportedLocaleCodes = listOf(
        "cs", "da", "de", "el", "en", "et",
        "fi", "hu", "in", "is", "lv", "nl",
        "no", "pl", "ro", "sl", "sv", "tr"
    )

    private val supportedLanguages = listOf(
        "en", "fr", "it", "de", "es", "pt",
        "nl", "da", "fi", "is", "no", "sv",
        "el", "cs", "sk", "hu", "pl", "ro",
        "bg", "sl", "tr", "ru", "bs", "hr",
        "sr", "et", "lt", "lv", "in", "be",
        "uk"
    )

    private val locale: Locale
        get() = applicationPreferences.getLocale()

    private val language: Language
        get() = Language.ofLocale(locale)

    val commonClipData by mutableStateOf(
        assetManager.readAsClipData(gson, "common")
    )

    val backgroundImageBitmap by mutableStateOf(
        assetManager.readAsImageBitmap("background_home")
    )

    val logoImageBitmap by mutableStateOf(
        readLogoImageBitmap()
    )


    private fun readLogoImageBitmap(): ImageBitmap {
        val localeCode = language.localeCode
        val safeLocaleCode = logoSupportedLocaleCodes.firstOrNull {
            it == localeCode
        } ?: "en"
        return assetManager.readAsImageBitmap(
            fileName = "logo_${safeLocaleCode}"
        )
    }
}