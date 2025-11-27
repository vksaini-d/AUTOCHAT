package com.example.autochat.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun HypnoticRing(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "RingAnimation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(10000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Rotation"
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2
        val maxRadius = size.minDimension / 2

        // Draw 16 rings
        for (i in 0 until 16) {
            val hue = (i * 20 + rotation) % 360
            val color = Color.hsv(hue, 1f, 1f)
            val radius = maxRadius - (i * 20)
            
            if (radius > 0) {
                drawCircle(
                    color = color,
                    radius = radius,
                    center = Offset(centerX, centerY),
                    style = Stroke(width = 2.dp.toPx())
                )
            }
        }
    }
}
