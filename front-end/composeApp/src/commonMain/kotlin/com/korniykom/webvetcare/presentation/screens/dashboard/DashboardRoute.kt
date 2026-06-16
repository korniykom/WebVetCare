package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface DashboardRoute: NavKey {
    @Serializable
    data object Profile: DashboardRoute
    @Serializable
    data object BecomeDoctor: DashboardRoute
    @Serializable
    data object BecomePatient: DashboardRoute
    @Serializable
    data object PatientProfile: DashboardRoute
    @Serializable
    data object DoctorProfile: DashboardRoute
}