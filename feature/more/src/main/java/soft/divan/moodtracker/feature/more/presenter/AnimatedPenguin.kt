package soft.divan.moodtracker.feature.more.presenter

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp


@Composable
fun AnimatedPenguin(modifier: Modifier = Modifier) {
    val angle by rememberInfiniteTransition(label = "wing_animation")
        .animateFloat(
            initialValue = -120f,
            targetValue = 120f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 1000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ),
            label = "wing_angle"
        )

    Canvas(modifier = modifier.size(200.dp)) {
        val canvasWidth = size.width
        val canvasHeight = size.height


        val darkBlue = Color(0xFF2C3E50)
        val lightCream = Color(0xFFF7F2EB)
        val orange = Color(0xFFF39C12)

        val wingTopLeft = Offset(canvasWidth * 0.1f, canvasHeight * 0.4f)
        val wingSize = Size(canvasWidth * 0.2f, canvasHeight * 0.4f)
        val wingPivot =
            Offset(wingTopLeft.x + wingSize.width / 2f, wingTopLeft.y)


        rotate(degrees = angle, pivot = wingPivot) {
            drawOval(
                color = darkBlue,
                topLeft = wingTopLeft,
                size = wingSize
            )
        }


        drawOval(
            color = darkBlue,
            topLeft = Offset(canvasWidth * 0.7f, canvasHeight * 0.4f),
            size = wingSize
        )


        drawOval(
            color = darkBlue,
            topLeft = Offset(canvasWidth * 0.15f, canvasHeight * 0.2f),
            size = Size(canvasWidth * 0.7f, canvasHeight * 0.65f)
        )


        drawOval(
            color = lightCream,
            topLeft = Offset(canvasWidth * 0.35f, canvasHeight * 0.4f),
            size = Size(canvasWidth * 0.3f, canvasHeight * 0.4f)
        )


        drawCircle(
            color = darkBlue,
            radius = canvasWidth * 0.28f,
            center = Offset(canvasWidth * 0.5f, canvasHeight * 0.35f)
        )


        drawOval(
            color = lightCream,
            topLeft = Offset(canvasWidth * 0.25f, canvasHeight * 0.15f),
            size = Size(canvasWidth * 0.5f, canvasHeight * 0.4f)
        )


        drawOval(
            color = orange,
            topLeft = Offset(canvasWidth * 0.35f, canvasHeight * 0.82f),
            size = Size(canvasWidth * 0.1f, canvasHeight * 0.07f)
        )
        drawOval(
            color = orange,
            topLeft = Offset(canvasWidth * 0.55f, canvasHeight * 0.82f),
            size = Size(canvasWidth * 0.1f, canvasHeight * 0.07f)
        )

        val beakCenterX = canvasWidth * 0.5f
        val beakCenterY = canvasHeight * 0.48f
        drawIntoCanvas {
            val path = androidx.compose.ui.graphics.Path().apply {
                moveTo(beakCenterX, beakCenterY)
                lineTo(beakCenterX - 20f, beakCenterY + 15f)
                lineTo(beakCenterX + 20f, beakCenterY + 15f)
                close()
            }
            it.drawPath(path, Paint().apply { color = orange })
        }


        drawArc(
            color = darkBlue,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(canvasWidth * 0.33f, canvasHeight * 0.32f),
            size = Size(30f, 20f),
            style = Stroke(width = 5f)
        )
        drawArc(
            color = darkBlue,
            startAngle = 180f,
            sweepAngle = 180f,
            useCenter = false,
            topLeft = Offset(canvasWidth * 0.57f, canvasHeight * 0.32f),
            size = Size(30f, 20f),
            style = Stroke(width = 5f)
        )
    }
}
