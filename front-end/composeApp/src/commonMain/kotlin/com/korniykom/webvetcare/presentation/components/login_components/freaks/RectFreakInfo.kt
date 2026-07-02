package com.korniykom.webvetcare.presentation.components.login_components.freaks

import androidx.compose.ui.graphics.Color

data class RectFreakInfo(
    val bodyColor: Color,
    val topLeftPointX: Float,
    val topLeftPointY: Float,
    val topRightPointX: Float,
    val topRightPointY: Float,
    val bottomLeftPointX: Float,
    val bottomLeftPointY: Float,
    val bottomRightPointX: Float,
    val bottomRightPointY: Float,
    val innerEyeColor: Color,
    val outerEyeColor: Color,
    val outerRightEyePositionX: Float,
    val outerRightEyePositionY: Float,
    val outerLeftEyePositionX: Float,
    val outerLeftEyePositionY: Float,
    val innerRightEyePositionX: Float,
    val innerRightEyePositionY: Float,
    val innerLeftEyePositionX: Float,
    val innerLeftEyePositionY: Float,
)