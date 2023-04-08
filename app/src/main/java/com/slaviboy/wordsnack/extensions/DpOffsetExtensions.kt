package com.slaviboy.wordsnack.extensions

import androidx.compose.ui.unit.DpOffset
import com.slaviboy.wordsnack.core.Math.PI
import com.slaviboy.wordsnack.core.Math.cos
import com.slaviboy.wordsnack.core.Math.sin

fun DpOffset.rotateAroundPivot(
    pivot: DpOffset,
    angle: Float
): DpOffset {
    val cx = pivot.x
    val cy = pivot.y
    val radians = (PI / 180f) * angle
    val cos = cos(radians)
    val sin = sin(radians)
    val nx = ((x - cx) * cos) + ((y - cy) * sin) + cx
    val ny = ((y - cy) * cos) - ((x - cx) * sin) + cy
    return DpOffset(nx, ny)
}

fun DpOffset.positionBetweenPivotAtDistance(
    pivot: DpOffset,
    factor: Float //[0,1]
): DpOffset {
    val xDist = pivot.x - this.x
    val yDist = pivot.y - this.y
    val dist = (xDist * xDist + yDist * yDist).sqrt()
    val fractionOfTotal = (factor * 100f) / dist
    return DpOffset(
        x = this.x + xDist * fractionOfTotal,
        y = this.y + yDist * fractionOfTotal
    )
}