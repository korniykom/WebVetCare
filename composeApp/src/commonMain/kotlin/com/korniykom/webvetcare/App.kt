package com.korniykom.webvetcare

import androidx.compose.runtime.Composable
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageRoot
import com.korniykom.webvetcare.presentation.theme.WebVetCareTheme


@Composable
fun App() {
    WebVetCareTheme{
        LandingPageRoot()
    }
}