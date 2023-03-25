package com.slaviboy.wordsnack.entities

import java.util.Locale

sealed class Language(
    val localeCode: String,
    val name: String
) {
    object English : Language("en", "English")
    object Bulgarian : Language("bg", "Български")

    companion object {
        fun ofLocale(locale: Locale): Language {
            val language = locale.language
            val country = locale.country
            return when (language) {
                "en" -> English
                "bg" -> Bulgarian
                else -> English
            }
        }
    }
}