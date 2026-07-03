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
import com.korniykom.webvetcare.presentation.theme.Teal70

@Composable
fun TealSemiCircle(
    freaksLookState: FreaksLookState,
    freakInfo: SemiCircleInfo,
    modifier: Modifier = Modifier,
    fallOnDistance: Float = 600f,
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


    val fallOnOffset = remember { Animatable(fallOnDistance) }
    LaunchedEffect(Unit) {
        fallOnOffset.animateTo(
            targetValue = 0f,
            animationSpec = spring(
                dampingRatio = .8f,
                stiffness = 65f
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

        translate(top = fallOnOffset.value) {
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
        outerLeftEyeXOffset = 160f, outerLeftEyeYOffset =24.0f,
        innerLeftEyeXOffset = 61f, innerLeftEyeYOffset = 22.0f,
        outerRightEyeXOffset = 30f, outerRightEyeYOffset = 24.0f,
        innerRightEyeXOffset = 31f, innerRightEyeYOffset = 22.0f,

        )
    FreaksLookState.PASSWORD_FIELD_HIDDEN -> freakInfo.copy(
        xScaleOffset = 1.02f,
        xOffset = -8f,
        outerLeftEyeXOffset = 160f, outerLeftEyeYOffset =24.0f,
        innerLeftEyeXOffset = 61f, innerLeftEyeYOffset = 23.0f,
        outerRightEyeXOffset = 30f, outerRightEyeYOffset = 24.0f,
        innerRightEyeXOffset = 31f, innerRightEyeYOffset = 23.0f,
    )
    FreaksLookState.PASSWORD_FIELD_VISIBLE -> freakInfo.copy(
        xScaleOffset = .97f,
        xOffset = -58f,
        outerLeftEyeXOffset = 80f, outerLeftEyeYOffset =24.0f,
        innerLeftEyeXOffset = -21f, innerLeftEyeYOffset = 23.0f,
        outerRightEyeXOffset = -50f, outerRightEyeYOffset = 24.0f,
        innerRightEyeXOffset = -51f, innerRightEyeYOffset = 23.0f,
    )
    FreaksLookState.NONE -> freakInfo.copy(
        xScaleOffset = .97f,
        xOffset = -58f,
        outerLeftEyeXOffset = 100f, outerLeftEyeYOffset =24.0f,
        innerLeftEyeXOffset = 0f, innerLeftEyeYOffset = 24.0f,
        outerRightEyeXOffset = -30f, outerRightEyeYOffset = 24.0f,
        innerRightEyeXOffset = -30f, innerRightEyeYOffset = 24.0f,
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