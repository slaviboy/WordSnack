package com.slaviboy.wordsnack.extensions

import android.content.res.AssetManager

fun AssetManager.readAssetsFile(fileName: String): String {
    return open(fileName)
        .bufferedReader()
        .use { it.readText() }
}