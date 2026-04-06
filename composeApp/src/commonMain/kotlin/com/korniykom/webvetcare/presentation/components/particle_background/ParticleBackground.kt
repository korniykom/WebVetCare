package com.korniykom.webvetcare.presentation.components.particle_background

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import kotlin.random.Random

fun createParticles(
    count: Int,
    width: Float,
    height: Float,
    color: Color
): MutableList<Particle> {
    val size = Size(width, height)

    return MutableList(count) {
        Particle(
            x = Random.nextFloat() * width,
            y = Random.nextFloat() * height,
            vx = Random.nextFloat() * 0.1f,
            vy = -(Random.nextFloat()),
            radius = randomRadius(size) * .8f,
            alpha = Random.nextFloat() * 0.2f,
            color = color,
        )
    }
}

fun updateParticles(
    particles: MutableList<Particle>,
    delta: Float,
    canvasWidth: Float,
    canvasHeight: Float
) {
    for (i in particles.indices) {
        val p = particles[i]
        var newX = p.x + p.vx * delta
        var newY = p.y + p.vy * delta

        if (newY + p.radius < 0f) {
            newY = canvasHeight + p.radius
            newX = Random.nextFloat() * canvasWidth
        }

        particles[i] = p.copy(x = newX, y = newY)
    }
}

fun randomRadius(size: Size): Float {
    val base = size.minDimension
    return Random.nextFloat() * (base * 0.005f) + (base * 0.005f)
}

@Composable
fun ParticleBackground(
    backgroundGradientColorStart: Color,
    backgroundGradientColorEnd: Color,
    particlesColor: Color,
    modifier: Modifier = Modifier
) {
    var size by remember { mutableStateOf(Size.Zero) }
    val particles = remember { mutableStateListOf<Particle>() }

    LaunchedEffect(size) {
        if (size != Size.Zero && particles.isEmpty()) {
            particles.addAll(createParticles(96, size.width, size.height, particlesColor))
        }
    }

    LaunchedEffect(Unit) {
        var lastTime = 0L
        while (true) {
            val currentTime = withFrameMillis { it }
            val delta = if (lastTime == 0L) 0f else (currentTime - lastTime) / 32f
            lastTime = currentTime

            updateParticles(particles, delta, size.width, size.height)
        }
    }

    Canvas(
        modifier = modifier
            .fillMaxSize()
            .onSizeChanged { size = Size(it.width.toFloat(), it.height.toFloat()) }
    ) {
        val backgroundBrush = Brush.verticalGradient(
            colors = listOf(backgroundGradientColorStart, backgroundGradientColorEnd),
            startY = 0f,
            endY = size.height
        )

        drawRect(brush = backgroundBrush, size = size)

        particles.forEach { particle ->
            drawCircle(
                color = particle.color.copy(alpha = particle.alpha),
                radius = particle.radius,
                center = Offset(particle.x, particle.y)
            )
        }
    }
}