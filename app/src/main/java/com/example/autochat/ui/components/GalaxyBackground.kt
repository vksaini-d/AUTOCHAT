package com.example.autochat.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import kotlin.math.*
import kotlin.random.Random

data class Star(
    var x: Float,
    var y: Float,
    var z: Float,
    val color: Color,
    val initialAngle: Float,
    val distance: Float,
    val speed: Float
)

@Composable
fun GalaxyBackground(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "GalaxyAnimation")
    val time by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "Time"
    )

    val stars = remember {
        List(500) {
            val distance = Random.nextFloat() * 1000f
            val angle = Random.nextFloat() * 2 * PI.toFloat()
            val color = when (Random.nextInt(3)) {
                0 -> Color(0xFFFF00FF) // Pink
                1 -> Color(0xFF00FFFF) // Cyan
                else -> Color(0xFFFFA500) // Orange
            }
            Star(
                x = 0f, y = 0f, z = 0f,
                color = color,
                initialAngle = angle,
                distance = distance,
                speed = 0.5f + Random.nextFloat()
            )
        }
    }

    Canvas(modifier = modifier.fillMaxSize()) {
        val centerX = size.width / 2
        val centerY = size.height / 2

        // Draw background
        drawRect(color = Color(0xFF050510))

        with(drawContext.canvas.nativeCanvas) {
            val checkPoint = saveLayer(null, null)

            stars.forEach { star ->
                // Calculate position based on spiral
                val currentAngle = star.initialAngle + (time * 0.01f * star.speed)
                // Spiral effect: radius increases with angle, but we want a rotating galaxy
                // Let's simulate a 3D rotation by squashing Y
                
                val radius = star.distance
                val x = centerX + cos(currentAngle) * radius
                val y = centerY + sin(currentAngle) * radius * 0.6f // Tilt effect

                // Simple depth simulation for size/alpha
                val alpha = (1f - (radius / 1000f)).coerceIn(0.1f, 1f)
                
                drawCircle(
                    color = star.color.copy(alpha = alpha),
                    radius = 2f + (alpha * 3f),
                    center = Offset(x, y),
                    blendMode = BlendMode.Screen
                )
            }
            restoreToCount(checkPoint)
        }
    }
}
