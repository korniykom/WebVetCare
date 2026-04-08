package com.korniykom.webvetcare.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun adaptiveTextColor(background: Color): Color {
    return if ((background.red * 0.299 + background.green * 0.587 + background.blue * 0.114) > 0.5) {
        Color.Black
    } else {
        Color.White
    }
}