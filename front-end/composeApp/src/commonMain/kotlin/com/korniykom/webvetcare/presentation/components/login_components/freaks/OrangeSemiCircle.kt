package com.korniykom.webvetcare.presentation.components.login_components.freaks


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import com.korniykom.webvetcare.presentation.screens.login.FreaksLookState

@Composable
fun OrangeSemiCircle(
    freaksLookState: FreaksLookState,
    freakInfo: SemiCircleInfo,
    modifier: Modifier = Modifier,
    slideInDistance: Float = 600f,
    ) {
    val transition = updateTransition(
        targetState = freaksLookState,
        label = "SemiCircleFreak",
    )

    val scaleOffsetX by transition.animateFloat(
        label = "scaleOffsetX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).xScaleOffset }

    val scaleOffsetY by transition.animateFloat(
        label = "scaleOffsetY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).yScaleOffset }

    val offsetX by transition.animateFloat(
        label = "offsetX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).xOffset }

    val offsetY by transition.animateFloat(
        label = "offsetY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).yOffset }

    val outerLeftEyeOffsetX by transition.animateFloat(
        label = "outerLeftEyeOffsetX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).outerLeftEyeXOffset }

    val outerLeftEyeOffsetY by transition.animateFloat(
        label = "outerLeftEyeOffsetY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).outerLeftEyeYOffset }

    val outerRightEyeOffsetX by transition.animateFloat(
        label = "outerRightEyeOffsetX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).outerRightEyeXOffset }

    val outerRightEyeOffsetY by transition.animateFloat(
        label = "outerRightEyeOffsetY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).outerRightEyeYOffset }

    val innerLeftEyeOffsetX by transition.animateFloat(
        label = "innerLeftEyeOffsetX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).innerLeftEyeXOffset }

    val innerLeftEyeOffsetY by transition.animateFloat(
        label = "innerLeftEyeOffsetY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).innerLeftEyeYOffset }

    val innerRightEyeOffsetX by transition.animateFloat(
        label = "innerRightEyeOffsetX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).innerRightEyeXOffset }

    val innerRightEyeOffsetY by transition.animateFloat(
        label = "innerRightEyeOffsetY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakScale(state, freakInfo).innerRightEyeYOffset }

    val slideInOffset = remember { Animatable(slideInDistance) }
    LaunchedEffect(Unit) {
        slideInOffset.animateTo(
            targetValue = 0f,
            animationSpec = spring(
                dampingRatio = 0.5f,
                stiffness = 80f
            )
        )
    }

    Canvas(
        modifier = modifier
            .graphicsLayer {
                this.scaleX = scaleOffsetX
                this.scaleY = scaleOffsetY
            }
    ) {

        val centerX = size.width / 2 + freakInfo.xOffset
        val centerY = size.height / 2 + freakInfo.yOffset

        translate(left = slideInOffset.value ) {
            drawArc(
                color = freakInfo.color,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = true,
                topLeft = Offset(centerX + offsetX , centerY + offsetY ),
                size = Size(freakInfo.radius * 2, freakInfo.radius * 2),
                style = Fill
            )
            drawEyes(
                freakInfo = freakInfo,
                centerX = centerX,
                centerY = centerY,
                innerRightEyePositionX = innerRightEyeOffsetX,
                innerRightEyePositionY = innerRightEyeOffsetY,
                outerRightEyePositionX = outerRightEyeOffsetX,
                outerRightEyePositionY = outerRightEyeOffsetY,
                innerLeftEyePositionX = innerLeftEyeOffsetX,
                innerLeftEyePositionY = innerLeftEyeOffsetY,
                outerLeftEyePositionX = outerLeftEyeOffsetX,
                outerLeftEyePositionY = outerLeftEyeOffsetY,
            )
        }
    }
}

private fun changeFreakScale(state: FreaksLookState, freakInfo: SemiCircleInfo): SemiCircleInfo = when (state) {
    FreaksLookState.EMAIL_FIELD -> freakInfo.copy(
        xScaleOffset = 1.05f,
        xOffset = -10f,
        outerLeftEyeXOffset = 260f, outerLeftEyeYOffset = 0.0f,
        innerLeftEyeXOffset = 112f, innerLeftEyeYOffset = -0.7f,
        outerRightEyeXOffset = 60f, outerRightEyeYOffset = 0.0f,
        innerRightEyeXOffset = 62f, innerRightEyeYOffset = -.7f,

    )
    FreaksLookState.PASSWORD_FIELD_HIDDEN -> freakInfo.copy(
        xScaleOffset = 1.02f,
        xOffset = -8f,
        outerLeftEyeXOffset = 260f, outerLeftEyeYOffset = 0.0f,
        innerLeftEyeXOffset = 112f, innerLeftEyeYOffset = .3f,
        outerRightEyeXOffset = 60f, outerRightEyeYOffset = 0.0f,
        innerRightEyeXOffset = 62f, innerRightEyeYOffset = .3f,
        )
    FreaksLookState.PASSWORD_FIELD_VISIBLE -> freakInfo.copy(
        xScaleOffset = .97f,
        xOffset = -58f,
        outerLeftEyeXOffset = 180f, outerLeftEyeYOffset = 0.0f,
        innerLeftEyeXOffset = 28f, innerLeftEyeYOffset = -1.3f,
        outerRightEyeXOffset = -30f, outerRightEyeYOffset = 0.0f,
        innerRightEyeXOffset = -32f, innerRightEyeYOffset = -1.3f,
        )
    FreaksLookState.NONE -> freakInfo.copy(
        xScaleOffset = .97f,
        xOffset = -58f,
        outerLeftEyeXOffset = 220f, outerLeftEyeYOffset = 0.0f,
        innerLeftEyeXOffset = 70f, innerLeftEyeYOffset = 0.0f,
        outerRightEyeXOffset = 10f, outerRightEyeYOffset = 0.0f,
        innerRightEyeXOffset = 10f, innerRightEyeYOffset = 0.0f,
        )
}

private fun DrawScope.drawEyes(
    freakInfo: SemiCircleInfo,
    centerX: Float,
    centerY: Float,
    innerRightEyePositionX: Float, innerRightEyePositionY: Float,
    outerRightEyePositionX: Float, outerRightEyePositionY: Float,
    innerLeftEyePositionX: Float, innerLeftEyePositionY: Float,
    outerLeftEyePositionX: Float, outerLeftEyePositionY: Float,
) {
    drawCircle(
        color = freakInfo.outerEyeColor,
        radius = 8f,
        center = Offset(
            x = centerX + freakInfo.radius / 2 + outerRightEyePositionX + freakInfo.outerRightEyeXOffset,
            y = centerY + freakInfo.radius / 2 + outerRightEyePositionY + freakInfo.outerRightEyeYOffset
        ),
    )

    drawCircle(
        color = freakInfo.outerEyeColor,
        radius = 8f,
        center = Offset(
            x = centerX - freakInfo.radius / 2 + outerLeftEyePositionX + freakInfo.outerLeftEyeXOffset,
            y = centerY + freakInfo.radius / 2 + outerLeftEyePositionY + freakInfo.outerLeftEyeYOffset
        ),
    )
    drawCircle(
        color = freakInfo.innerEyeColor,
        radius = 4f,
        center = Offset(
            x = centerX + freakInfo.radius / 2 + innerRightEyePositionX + freakInfo.innerRightEyeXOffset,
            y = centerY + freakInfo.radius / 2 + innerRightEyePositionY + freakInfo.innerRightEyeYOffset
        ),
    )
    drawCircle(
        color = freakInfo.innerEyeColor,
        radius = 4f,
        center = Offset(
            x = centerX + freakInfo.radius / 2 + innerLeftEyePositionX + freakInfo.innerLeftEyeXOffset,
            y = centerY + freakInfo.radius / 2 + innerLeftEyePositionY + freakInfo.innerLeftEyeYOffset
        ),
    )
}