package com.korniykom.webvetcare.presentation.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {
    @Serializable
    data object LandingPage : Route, NavKey

    @Serializable
    data object LoginScreen : Route, NavKey

    @Serializable
    data object RegisterScreen : Route, NavKey
}