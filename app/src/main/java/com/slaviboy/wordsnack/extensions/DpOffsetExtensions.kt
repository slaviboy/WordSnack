package com.slaviboy.wordsnack.extensions

import androidx.compose.ui.unit.DpOffset

fun DpOffset.rotateAroundPivot(
    pivot: DpOffset,
    angle: Float
): DpOffset {
    val cx = pivot.x
    val cy = pivot.y
    val radians = (Math.PI / 180f) * angle
    val cos = Math.cos(radians).toFloat()
    val sin = Math.sin(radians).toFloat()
    val nx = ((x - cx) * cos) + ((y - cy) * sin) + cx
    val ny = ((y - cy) * cos) - ((x - cx) * sin) + cy
    return DpOffset(nx, ny)
}