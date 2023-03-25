package com.slaviboy.wordsnack.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.app.ActivityCompat

@SuppressLint("DiscouragedApi")
fun Context.getBitmap(name: String): Bitmap {
    val resId = this.resources.getIdentifier(name, "drawable", this.packageName)
    val drawable = ActivityCompat.getDrawable(this, resId)
    val bitmap = (drawable as BitmapDrawable).bitmap
    return bitmap ?: throw Exception("Drawable file not found!")
}