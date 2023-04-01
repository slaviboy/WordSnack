package com.slaviboy.wordsnack.extensions

import android.graphics.PointF
import androidx.compose.ui.graphics.Path
import com.slaviboy.wordsnack.core.Math

fun Path.setLinesThroughPoints(
    points: List<PointF>
) {
    for (i in points.indices) {
        if (i == 0) {
            moveTo(points[0].x, points[0].y)
        } else {
            lineTo(points[i].x, points[i].y)
        }
    }
}

fun Path.setCurveThroughPoints(
    points: List<PointF>,
    tension: Float = 2f
) {
    if (points.size < 2) {
        return
    }
    if (points.size == 2) {
        moveTo(points[0].x, points[0].y)
        lineTo(points[1].x, points[1].y)
        return
    }
    var prevM2x = 0f
    var prevM2y = 0f
    val len = points.size
    for (i in 1 until len - 1) {
        val p0 = points[i - 1]
        val p1 = points[i]
        val p2 = points[i + 1]
        var tx = p2.x - (if (i == 1) p0.x else prevM2x)
        var ty = p2.y - (if (i == 1) p0.y else prevM2y)
        val tLen = Math.sqrt(tx * tx + ty * ty)
        if (tLen > 1e-8) {
            val inv = 1f / tLen
            tx *= inv
            ty *= inv
        } else {
            tx = 0f
            ty = 0f
        }
        val det = Math.sqrt(
            Math.min(
                (p0.x - p1.x) * (p0.x - p1.x) + (p0.y - p1.y) * (p0.y - p1.y),
                (p2.x - p1.x) * (p2.x - p1.x) + (p2.y - p1.y) * (p2.y - p1.y)
            )
        ).toFloat() / (2f * tension)
        val m1x = p1.x - tx * det
        val m1y = p1.y - ty * det
        val m2x = p1.x + tx * det
        val m2y = p1.y + ty * det
        if (i == 1) {
            moveTo(p0.x, p0.y)
            quadraticBezierTo(m1x, m1y, p1.x, p1.y)
        } else {
            val mx = (prevM2x + m1x) / 2
            val my = (prevM2y + m1y) / 2
            quadraticBezierTo(prevM2x, prevM2y, mx, my)
            quadraticBezierTo(m1x, m1y, p1.x, p1.y)
        }
        if (i == len - 2) {
            quadraticBezierTo(m2x, m2y, p2.x, p2.y)
        }
        prevM2x = m2x
        prevM2y = m2y
    }
}