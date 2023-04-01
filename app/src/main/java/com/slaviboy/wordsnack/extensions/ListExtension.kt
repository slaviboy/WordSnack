package com.slaviboy.wordsnack.extensions

import java.util.Random

fun List<String>.getRandomItems(numberOfItems: Int): List<String> {
    val shuffled = this.shuffled(Random())
    val max = Math.min(shuffled.size, numberOfItems)
    return shuffled.slice(0 until max)
}