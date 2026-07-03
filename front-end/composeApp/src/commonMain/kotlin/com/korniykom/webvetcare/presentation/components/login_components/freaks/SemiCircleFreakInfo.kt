package com.korniykom.webvetcare.presentation.components.login_components.freaks

import androidx.compose.ui.graphics.Color

data class SemiCircleInfo(
    val xScaleOffset: Float,
    val yScaleOffset: Float,
    val radius: Float,
    val xOffset: Float ,
    val yOffset: Float ,
    val color: Color,
    val outerEyeColor: Color,
    val innerEyeColor: Color,

    val outerRightEyeXOffset: Float = 0f,
    val outerRightEyeYOffset: Float = 0f,
    val outerLeftEyeXOffset: Float = 0f,
    val outerLeftEyeYOffset: Float = 0f,
    val innerLeftEyeXOffset: Float = 0f,
    val innerLeftEyeYOffset: Float = 0f,
    val innerRightEyeXOffset: Float = 0f,
    val innerRightEyeYOffset: Float = 0f,

    )