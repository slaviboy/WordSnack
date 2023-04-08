package com.slaviboy.wordsnack.extensions

fun String.shuffle(): String {
    return this.toCharArray().apply {
        shuffle()
    }.concatToString()
}