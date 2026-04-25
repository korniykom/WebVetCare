package com.korniykom.webvetcare.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object Dashboard: Route
    @Serializable
    data object LandingPage : Route

    @Serializable
    data object LoginScreen : Route

    @Serializable
    data object RegisterScreen : Route
}