package com.slaviboy.wordsnack.extensions

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import androidx.core.app.ActivityCompat
import java.util.Locale

@SuppressLint("DiscouragedApi")
fun Context.getBitmap(name: String): Bitmap {
    val resId = this.resources.getIdentifier(name, "drawable", this.packageName)
    val drawable = ActivityCompat.getDrawable(this, resId)
    val bitmap = (drawable as BitmapDrawable).bitmap
    return bitmap ?: throw Exception("Drawable file not found!")
}

fun Context.findActivity(): Activity? = when (this) {
    is Activity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

fun Context.updateLanguage(localeCode: String = "en") {
    val locale = Locale(localeCode)
    Locale.setDefault(locale)
    val resources = this.resources
    val configuration = resources.configuration
    configuration.locale = locale
    resources.updateConfiguration(configuration, resources.displayMetrics)
    this.findActivity()?.recreate()
}

fun Context.getCurrentLocale(): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        this.resources.configuration.locales[0]
    } else {
        this.resources.configuration.locale
    }
}