package com.korniykom.webvetcare.presentation.navigation.dashboard

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface DashboardRoute: NavKey {
    @Serializable
    data object Home: DashboardRoute
    @Serializable
    data object Patients: DashboardRoute
    @Serializable
    data object Settings: DashboardRoute
}