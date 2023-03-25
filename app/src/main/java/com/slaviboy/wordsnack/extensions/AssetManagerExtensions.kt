package com.slaviboy.wordsnack.extensions

import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.Gson
import com.slaviboy.wordsnack.entities.ClipConfig
import com.slaviboy.wordsnack.entities.ClipData
import java.io.IOException
import java.io.InputStream

fun AssetManager.readAsText(
    fileName: String,
    filePath: String = "config/",
    fileType: String = "json"
): String {
    return open("$filePath$fileName.$fileType")
        .bufferedReader()
        .use { it.readText() }
}

fun AssetManager.readAsBitmap(
    fileName: String,
    filePath: String = "img/",
    fileType: String = "png"
): Bitmap {
    val inputStream: InputStream
    val bitmap: Bitmap?
    try {
        inputStream = open("$filePath$fileName.$fileType")
        bitmap = BitmapFactory.decodeStream(inputStream)
    } catch (e: IOException) {
        throw Exception(e)
    }
    return bitmap
}

fun AssetManager.getClipData(
    gson: Gson,
    fileNames: String
): ClipData {
    val bitmap = readAsBitmap(fileNames)
    val string = readAsText(fileNames)
    val clipConfig = gson.fromJson(string, Array<ClipConfig>::class.java)
    return ClipData(bitmap, clipConfig.toMutableList())
}