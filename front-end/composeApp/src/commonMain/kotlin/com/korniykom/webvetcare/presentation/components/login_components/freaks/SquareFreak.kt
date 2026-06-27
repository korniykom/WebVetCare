package com.korniykom.webvetcare.presentation.components.login_components

import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.korniykom.webvetcare.presentation.components.login_components.freaks.SquareFreakBodyCorners
import com.korniykom.webvetcare.presentation.screens.login.FreaksLookState
import com.korniykom.webvetcare.presentation.theme.SatinDeepBlack
import com.korniykom.webvetcare.presentation.theme.WhiteAsHeaven

@Composable
fun SquareFreak(
    modifier: Modifier = Modifier,
    bodyColor: Color = SatinDeepBlack,
    bodyPoints: SquareFreakBodyCorners,
    eyeColor: Color = SatinDeepBlack,
    mouthColor: Color = SatinDeepBlack,
    xOffset: Int = 0,
    yOffset: Int = 0,
    freaksLookState: FreaksLookState,
) {

    val transition = updateTransition(
        targetState = freaksLookState,
        label = "FreakEyes"
    )

    Canvas(modifier = modifier) {
        var centerX = size.width / 2 + xOffset
        var centerY = size.height / 2 + yOffset
        var rightEyeX = -30f
        var rightEyeY = 20f
        var leftEyeX = 30f
        var leftEyeY = 20f

        when (freaksLookState) {
            FreaksLookState.EMAIL_FIELD -> {
                rightEyeX += 20
                rightEyeY += 20
                leftEyeX += 20
                leftEyeY += 20
            }

            FreaksLookState.PASSWORD_FIELD_HIDDEN -> {
                rightEyeX += 20
                rightEyeY += 25
                leftEyeX += 20
                leftEyeY += 25
            }

            FreaksLookState.PASSWORD_FIELD_VISIBLE -> {
                rightEyeX -= 20
                rightEyeY += 5
                leftEyeX -= 20
                leftEyeY += 5
            }

            FreaksLookState.NONE -> {
                rightEyeX += 0
                rightEyeY += 0
                leftEyeX += 0
                leftEyeY += 0
            }
        }

        DrawBody(bodyPoints, centerX, centerY, bodyColor)

        DrawUnderLeftEye(leftEyeX, leftEyeY, bodyPoints, centerX, centerY)

        DrawLeftEye(leftEyeX, leftEyeY, bodyPoints, centerX, centerY)

        DrawUnderRightEye(rightEyeX, rightEyeY, bodyPoints, centerX, centerY)

        DrawRightEye(rightEyeX, rightEyeY, bodyPoints, centerX, centerY, eyeColor)

    }
}

private fun DrawScope.DrawBody(
    bodyPoints: SquareFreakBodyCorners,
    centerX: Float,
    centerY: Float,
    bodyColor: Color
) {
    val bodyPath = Path().apply {
        moveTo(centerX + bodyPoints.topLeftX, centerY + bodyPoints.topLeftY)
        lineTo(centerX + bodyPoints.topRightX, centerY + bodyPoints.topRightY)
        lineTo(centerX + bodyPoints.bottomRightX, centerY + bodyPoints.bottomRightY)
        lineTo(centerX + bodyPoints.bottomLeftX, centerY + bodyPoints.bottomLeftY)
        close()
    }
    drawPath(bodyPath, color = bodyColor)
}

private fun DrawScope.DrawLeftEye(
    eyePositionX: Float,
    eyePositionY: Float,
    bodyPoints: SquareFreakBodyCorners,
    centerX: Float,
    centerY: Float,
) {
    drawCircle(
        color = SatinDeepBlack,
        radius = 4f,
        center = Offset(
            x = centerX + bodyPoints.bottomLeftX + eyePositionX,
            y = centerY + bodyPoints.bottomLeftY + eyePositionY
        ),
    )
}

private fun DrawScope.DrawUnderLeftEye(
    eyePositionX: Float,
    eyePositionY: Float,
    bodyPoints: SquareFreakBodyCorners,
    centerX: Float,
    centerY: Float,
) {
    drawCircle(
        color = WhiteAsHeaven,
        radius = 8f,
        center = Offset(
            x = centerX + bodyPoints.bottomLeftX + eyePositionX,
            y = centerY + bodyPoints.bottomLeftY + eyePositionY
        ),
    )
}

private fun DrawScope.DrawUnderRightEye(
    eyePositionX: Float,
    eyePositionY: Float,
    bodyPoints: SquareFreakBodyCorners,
    centerX: Float,
    centerY: Float
) {
    drawCircle(
        color = WhiteAsHeaven,
        radius = 8f,
        center = Offset(
            x = centerX + bodyPoints.bottomRightX + eyePositionX,
            y = centerY + bodyPoints.bottomRightY + eyePositionY
        ),
    )
}

private fun DrawScope.DrawRightEye(
    eyePositionX: Float,
    eyePositionY: Float,
    bodyPoints: SquareFreakBodyCorners,
    centerX: Float,
    centerY: Float,
    color: Color
) {
    drawCircle(
        color = color,
        radius = 4f,
        center = Offset(
            x = centerX + bodyPoints.bottomRightX + eyePositionX,
            y = centerY + bodyPoints.bottomRightY + eyePositionY
        ),
    )
}
