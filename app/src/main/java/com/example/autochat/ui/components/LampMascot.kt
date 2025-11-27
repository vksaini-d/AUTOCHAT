package com.example.autochat.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.sin

enum class LampState {
    SAD, HAPPY, SLEEPY
}

@Composable
fun LampMascot(
    modifier: Modifier = Modifier,
    state: LampState,
    onPull: () -> Unit
) {
    var stringOffsetY by remember { mutableFloatStateOf(0f) }
    val animatedStringY by animateFloatAsState(
        targetValue = stringOffsetY,
        animationSpec = spring(stiffness = Spring.StiffnessLow),
        label = "StringAnimation"
    )

    // Reset string position after pull
    LaunchedEffect(stringOffsetY) {
        if (stringOffsetY > 100f) {
            onPull()
            stringOffsetY = 0f
        } else if (stringOffsetY > 0f) {
            // Snap back if not pulled enough
            // In a real app we'd use Animatable for better control
            stringOffsetY = 0f 
        }
    }

    Canvas(
        modifier = modifier
            .size(200.dp, 300.dp)
            .pointerInput(Unit) {
                detectVerticalDragGestures(
                    onDragEnd = { stringOffsetY = 0f },
                    onDragCancel = { stringOffsetY = 0f }
                ) { change, dragAmount ->
                    // Only allow dragging the string area (simplified)
                    if (change.position.y > 150.dp.toPx()) {
                        stringOffsetY = (stringOffsetY + dragAmount).coerceAtLeast(0f)
                    }
                }
            }
    ) {
        val centerX = size.width / 2
        val lampHeadY = 50.dp.toPx()
        
        // Draw Lamp Base (Pole)
        drawLine(
            color = Color.Gray,
            start = Offset(centerX, lampHeadY),
            end = Offset(centerX, size.height - 20.dp.toPx()),
            strokeWidth = 10.dp.toPx(),
            cap = StrokeCap.Round
        )
        
        // Draw Base Stand
        drawOval(
            color = Color.LightGray,
            topLeft = Offset(centerX - 40.dp.toPx(), size.height - 30.dp.toPx()),
            size = Size(80.dp.toPx(), 20.dp.toPx())
        )

        // Draw Lamp Shade (Trapezoid-ish)
        val shadePath = Path().apply {
            moveTo(centerX - 40.dp.toPx(), lampHeadY + 60.dp.toPx()) // Bottom Left
            lineTo(centerX + 40.dp.toPx(), lampHeadY + 60.dp.toPx()) // Bottom Right
            lineTo(centerX + 30.dp.toPx(), lampHeadY) // Top Right
            lineTo(centerX - 30.dp.toPx(), lampHeadY) // Top Left
            close()
        }
        drawPath(path = shadePath, color = Color(0xFF4CAF50)) // Green shade

        // Draw Face
        val faceCenterY = lampHeadY + 35.dp.toPx()
        if (state == LampState.HAPPY) {
            // Eyes
            drawCircle(Color.Black, radius = 3.dp.toPx(), center = Offset(centerX - 15.dp.toPx(), faceCenterY))
            drawCircle(Color.Black, radius = 3.dp.toPx(), center = Offset(centerX + 15.dp.toPx(), faceCenterY))
            // Smile
            drawArc(
                color = Color.Black,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(centerX - 10.dp.toPx(), faceCenterY),
                size = Size(20.dp.toPx(), 10.dp.toPx()),
                style = Stroke(width = 2.dp.toPx())
            )
        } else {
            // Sad/Sleepy Eyes
            drawLine(Color.Black, Offset(centerX - 20.dp.toPx(), faceCenterY), Offset(centerX - 10.dp.toPx(), faceCenterY + 5.dp.toPx()), strokeWidth = 2.dp.toPx())
            drawLine(Color.Black, Offset(centerX + 20.dp.toPx(), faceCenterY), Offset(centerX + 10.dp.toPx(), faceCenterY + 5.dp.toPx()), strokeWidth = 2.dp.toPx())
            // Frown
            drawArc(
                color = Color.Black,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(centerX - 10.dp.toPx(), faceCenterY + 10.dp.toPx()),
                size = Size(20.dp.toPx(), 10.dp.toPx()),
                style = Stroke(width = 2.dp.toPx())
            )
        }

        // Draw Pull String
        val stringStart = Offset(centerX - 20.dp.toPx(), lampHeadY + 60.dp.toPx())
        val stringEnd = Offset(stringStart.x, stringStart.y + 50.dp.toPx() + animatedStringY)
        
        drawLine(
            color = Color.White,
            start = stringStart,
            end = stringEnd,
            strokeWidth = 2.dp.toPx()
        )
        drawCircle(
            color = Color.White,
            radius = 5.dp.toPx(),
            center = stringEnd
        )
    }
}
