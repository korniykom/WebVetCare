package com.korniykom.webvetcare

import androidx.compose.runtime.Composable
import com.korniykom.webvetcare.presentation.navigation.NavigationRoot
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardScreen
import com.korniykom.webvetcare.presentation.theme.WebVetCareTheme


@Composable
fun App() {
    WebVetCareTheme(
        darkTheme = true
    ) {
        NavigationRoot()
    }
}