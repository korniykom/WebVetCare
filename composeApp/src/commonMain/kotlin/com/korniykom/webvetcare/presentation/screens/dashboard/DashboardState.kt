package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.compose.ui.geometry.Size

data class DashboardState(
    val isMenuExpanded: Boolean = true,
    val currentTab: MenuOptions = MenuOptions.PROFILE,
    val rightPaneSize: Size =  Size.Zero,
    val userEmail: String = "",
    val username: String = "",
    val userRoles: List<String> = emptyList(),
)

enum class MenuOptions {
    PROFILE, BECOME_DOCTOR, BECOME_PATIENT, PATIENT_PROFILE, DOCTOR_PROFILE
}
