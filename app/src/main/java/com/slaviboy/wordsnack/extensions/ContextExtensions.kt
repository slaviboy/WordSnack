package com.slaviboy.wordsnack.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import androidx.core.app.ActivityCompat
import com.google.gson.Gson
import com.slaviboy.wordsnack.entities.ClipConfig
import com.slaviboy.wordsnack.entities.ClipData

@SuppressLint("DiscouragedApi")
fun Context.getBitmap(name: String): Bitmap {
    val resId = this.resources.getIdentifier(name, "drawable", this.packageName)
    val drawable = ActivityCompat.getDrawable(this, resId)
    val bitmap = (drawable as BitmapDrawable).bitmap
    return bitmap ?: throw Exception("Drawable file not found!")
}

fun Context.getClipData(
    gson: Gson,
    fileNames: String
): ClipData {
    val bitmap = getBitmap(fileNames)
    val string = assets.readAssetsFile("img/${fileNames}.json")
    val clipConfig = gson.fromJson(string, Array<ClipConfig>::class.java)
    return ClipData(bitmap, clipConfig.toMutableList())
}