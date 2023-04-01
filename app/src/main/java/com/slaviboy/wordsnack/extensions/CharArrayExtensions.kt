package com.slaviboy.wordsnack.extensions

fun CharArray.hasSameChars(array: CharArray): Boolean {
    if (this.size > array.size) return false
    val arrayCopy = array.clone()
    for (c in this) {
        for (i in arrayCopy.indices) {
            if (c == arrayCopy[i]) {
                arrayCopy[i] = '-'
                break
            }
            if (i == arrayCopy.size - 1) return false
        }
    }
    return true
}