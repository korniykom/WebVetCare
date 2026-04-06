package com.korniykom.webvetcare

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.korniykom.webvetcare.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "WebVetCare",
    ) {
        initKoin()
        App()
    }
}