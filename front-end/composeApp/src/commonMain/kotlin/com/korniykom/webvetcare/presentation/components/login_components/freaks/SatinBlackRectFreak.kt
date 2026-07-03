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
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.translate
import com.korniykom.webvetcare.presentation.screens.login.FreaksLookState


@Composable
fun SatinBlackRectFreak(
    freakInfo: RectFreakInfo,
    xOffset: Float = 0f,
    yOffset: Float = 0f,
    modifier: Modifier = Modifier,
    freaksLookState: FreaksLookState,
    fallOnDistance: Float = 600f,
    ) {
    val transition = updateTransition(
        targetState = freaksLookState,
        label = "Freak"
    )

    val topLeftPointX by transition.animateFloat(
        label = "topLeftPointX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).topLeftPointX }

    val topLeftPointY by transition.animateFloat(
        label = "topLeftPointY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).topLeftPointY }

    val topRightPointX by transition.animateFloat(
        label = "topRightPointX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).topRightPointX }

    val topRightPointY by transition.animateFloat(
        label = "topRightPointY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).topRightPointY }

    val bottomLeftPointX by transition.animateFloat(
        label = "bottomLeftPointX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).bottomLeftPointX }

    val bottomLeftPointY by transition.animateFloat(
        label = "bottomLeftPointY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).bottomLeftPointY }

    val bottomRightPointX by transition.animateFloat(
        label = "bottomRightPointX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).bottomRightPointX }

    val bottomRightPointY by transition.animateFloat(
        label = "bottomRightPointY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).bottomRightPointY }

    val innerRightEyePositionX by transition.animateFloat(
        label = "innerRightEyePositionX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).innerRightEyePositionX }

    val innerRightEyePositionY by transition.animateFloat(
        label = "innerRightEyePositionY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).innerRightEyePositionY }

    val outerRightEyePositionX by transition.animateFloat(
        label = "outerRightEyePositionX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).outerRightEyePositionX }

    val outerRightEyePositionY by transition.animateFloat(
        label = "outerRightEyePositionY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).outerRightEyePositionY }

    val innerLeftEyePositionX by transition.animateFloat(
        label = "innerLeftEyePositionX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).innerLeftEyePositionX }

    val innerLeftEyePositionY by transition.animateFloat(
        label = "innerLeftEyePositionY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).innerLeftEyePositionY }

    val outerLeftEyePositionX by transition.animateFloat(
        label = "outerLeftEyePositionX",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).outerLeftEyePositionX }

    val outerLeftEyePositionY by transition.animateFloat(
        label = "outerLeftEyePositionY",
        transitionSpec = { spring(dampingRatio = 0.55f, stiffness = 300f) }
    ) { state -> changeFreakFloatValues(state, freakInfo).outerLeftEyePositionY }


    val fallOnOffset = remember { Animatable(-fallOnDistance) }
    LaunchedEffect(Unit) {
        fallOnOffset.animateTo(
            targetValue = 0f,
            animationSpec = spring(
                dampingRatio = .8f,
                stiffness = 65f
            )
        )
    }

    Canvas(modifier) {

        val centerX = size.width / 2 + xOffset
        val centerY = size.height / 2 + yOffset

        translate(top = fallOnOffset.value) {
            drawBody(
                freakInfo,
                centerX, centerY,
                topLeftPointX, topLeftPointY,
                topRightPointX, topRightPointY,
                bottomLeftPointX, bottomLeftPointY,
                bottomRightPointX, bottomRightPointY,
                innerRightEyePositionX, innerRightEyePositionY,
                outerRightEyePositionX, outerRightEyePositionY,
                innerLeftEyePositionX, innerLeftEyePositionY,
                outerLeftEyePositionX, outerLeftEyePositionY,


                )

        }
    }
}

private fun DrawScope.drawBody(
    freakInfo: RectFreakInfo,
    centerX: Float,
    centerY: Float,
    topLeftPointX: Float,
    topLeftPointY: Float,
    topRightPointX: Float,
    topRightPointY: Float,
    bottomLeftPointX: Float,
    bottomLeftPointY: Float,
    bottomRightPointX: Float,
    bottomRightPointY: Float,
    innerRightEyePositionX: Float, innerRightEyePositionY: Float,
    outerRightEyePositionX: Float, outerRightEyePositionY: Float,
    innerLeftEyePositionX: Float, innerLeftEyePositionY: Float,
    outerLeftEyePositionX: Float, outerLeftEyePositionY: Float,

    ) {
    val bodyPath = Path().apply {
        moveTo(centerX + topLeftPointX, centerY + topLeftPointY)
        lineTo(centerX + topRightPointX, centerY + topRightPointY)
        lineTo(centerX + bottomRightPointX, centerY + bottomRightPointY)
        lineTo(centerX + bottomLeftPointX, centerY + bottomLeftPointY)
        close()
    }
    drawPath(bodyPath, color = freakInfo.bodyColor)

    drawEyes(
        freakInfo = freakInfo,
        centerX = centerX,
        centerY = centerY,
        innerRightEyePositionX, innerRightEyePositionY,
        outerRightEyePositionX, outerRightEyePositionY,
        innerLeftEyePositionX, innerLeftEyePositionY,
        outerLeftEyePositionX, outerLeftEyePositionY,
    )
}

private fun DrawScope.drawEyes(
    freakInfo: RectFreakInfo,
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
            x = centerX + freakInfo.bottomRightPointX + outerRightEyePositionX,
            y = centerY + freakInfo.bottomRightPointY + outerRightEyePositionY
        ),
    )

    drawCircle(
        color = freakInfo.outerEyeColor,
        radius = 8f,
        center = Offset(
            x = centerX + freakInfo.bottomLeftPointX + outerLeftEyePositionX,
            y = centerY + freakInfo.bottomLeftPointY + outerLeftEyePositionY
        ),
    )
    drawCircle(
        color = freakInfo.innerEyeColor,
        radius = 4f,
        center = Offset(
            x = centerX + freakInfo.bottomRightPointX + innerRightEyePositionX,
            y = centerY + freakInfo.bottomRightPointY + innerRightEyePositionY
        ),
    )
    drawCircle(
        color = freakInfo.innerEyeColor,
        radius = 4f,
        center = Offset(
            x = centerX + freakInfo.bottomLeftPointX + innerLeftEyePositionX,
            y = centerY + freakInfo.bottomLeftPointY + innerLeftEyePositionY
        ),
    )
}


private fun changeFreakFloatValues(state: FreaksLookState, freakInfo: RectFreakInfo): RectFreakInfo = when (state) {
    FreaksLookState.EMAIL_FIELD -> freakInfo.copy(
        bottomLeftPointX = freakInfo.bottomLeftPointX + 10,
        bottomLeftPointY = freakInfo.bottomLeftPointY + 20,
        bottomRightPointX = freakInfo.bottomRightPointX + 10,
        bottomRightPointY = freakInfo.bottomRightPointY + 30,
        outerLeftEyePositionX = freakInfo.outerLeftEyePositionX + 25,
        outerLeftEyePositionY = freakInfo.outerLeftEyePositionY + 5f,
        outerRightEyePositionX = freakInfo.outerRightEyePositionX + 25,
        outerRightEyePositionY = freakInfo.outerRightEyePositionY + 5f,
        innerLeftEyePositionX = freakInfo.innerLeftEyePositionX + 26,
        innerLeftEyePositionY = freakInfo.innerLeftEyePositionY + 4,
        innerRightEyePositionX = freakInfo.innerRightEyePositionX + 26,
        innerRightEyePositionY = freakInfo.innerRightEyePositionY + 4,

        )

    FreaksLookState.PASSWORD_FIELD_HIDDEN -> freakInfo.copy(
        bottomLeftPointX = freakInfo.bottomLeftPointX + 10,
        bottomLeftPointY = freakInfo.bottomLeftPointY + 25,
        bottomRightPointX = freakInfo.bottomRightPointX + 10,
        bottomRightPointY = freakInfo.bottomRightPointY + 35,
        outerLeftEyePositionX = freakInfo.outerLeftEyePositionX + 25,
        outerLeftEyePositionY = freakInfo.outerLeftEyePositionY + 15f,
        outerRightEyePositionX = freakInfo.outerRightEyePositionX + 25,
        outerRightEyePositionY = freakInfo.outerRightEyePositionY + 15f,
        innerLeftEyePositionX = freakInfo.innerLeftEyePositionX + 26,
        innerLeftEyePositionY = freakInfo.innerLeftEyePositionY + 17,
        innerRightEyePositionX = freakInfo.innerRightEyePositionX + 26,
        innerRightEyePositionY = freakInfo.innerRightEyePositionY + 17,
    )

    FreaksLookState.PASSWORD_FIELD_VISIBLE -> freakInfo.copy(
        bottomLeftPointX = freakInfo.bottomLeftPointX - 20,
        bottomLeftPointY = freakInfo.bottomLeftPointY - 30,
        bottomRightPointX = freakInfo.bottomRightPointX - 30,
        bottomRightPointY = freakInfo.bottomRightPointY - 30,
        outerLeftEyePositionX = freakInfo.outerLeftEyePositionX - 26,
        outerLeftEyePositionY = freakInfo.outerLeftEyePositionY - 15f,
        outerRightEyePositionX = freakInfo.outerRightEyePositionX - 26,
        outerRightEyePositionY = freakInfo.outerRightEyePositionY - 15f,
        innerLeftEyePositionX = freakInfo.innerLeftEyePositionX - 28,
        innerLeftEyePositionY = freakInfo.innerLeftEyePositionY - 17.5f,
        innerRightEyePositionX = freakInfo.innerRightEyePositionX - 28,
        innerRightEyePositionY = freakInfo.innerRightEyePositionY - 17.5f,
    )

    FreaksLookState.NONE -> freakInfo.copy()
}