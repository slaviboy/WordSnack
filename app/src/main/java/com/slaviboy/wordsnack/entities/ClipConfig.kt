package com.slaviboy.wordsnack.entities

data class ClipConfig(
    val name: String,
    val x: Int = 1309,
    val y: Int = 410,
    val width: Int = 109,
    val height: Int = 130
) {
    val heightToWidth: Float
        get() = height / width.toFloat()

    val widthToHeight: Float
        get() = width / height.toFloat()
}