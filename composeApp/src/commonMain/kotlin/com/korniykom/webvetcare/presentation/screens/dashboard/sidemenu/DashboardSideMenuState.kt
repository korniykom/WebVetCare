package com.korniykom.webvetcare.presentation.screens.dashboard.sidemenu

data class DashboardSideMenuState(
    val currentTab: MenuOptions = MenuOptions.PROFILE
)

enum class MenuOptions {
    PROFILE, BECOME_DOCTOR, BECOME_PATIENT, PATIENT_PROFILE, DOCTOR_PROFILE
}
