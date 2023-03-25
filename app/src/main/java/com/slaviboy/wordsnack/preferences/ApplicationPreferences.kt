package com.slaviboy.wordsnack.preferences

import android.content.SharedPreferences
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApplicationPreferences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    fun getLocale(): Locale {
        return Locale(getLocaleLanguage(), getLocaleCountry())
    }

    fun setLocale(locale: Locale) {
        setLocaleLanguage(locale.language)
        setLocaleCountry(locale.country)
    }

    private fun getLocaleLanguage(): String {
        return sharedPreferences.getString(CURRENT_LOCALE_LANGUAGE, "en") ?: "en"
    }

    private fun setLocaleLanguage(language: String) {
        sharedPreferences.edit().putString(CURRENT_LOCALE_LANGUAGE, language).apply()
    }

    private fun getLocaleCountry(): String {
        return sharedPreferences.getString(CURRENT_LOCALE_COUNTRY, "") ?: ""
    }

    private fun setLocaleCountry(country: String) {
        sharedPreferences.edit().putString(CURRENT_LOCALE_COUNTRY, country).apply()
    }

    companion object {
        const val CURRENT_LOCALE_LANGUAGE = "CURRENT_LOCALE_LANGUAGE"
        const val CURRENT_LOCALE_COUNTRY = "CURRENT_LOCALE_COUNTRY"
    }
}