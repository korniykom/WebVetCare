package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.geometry.Size

data class DashboardState(
    val isMenuExpanded: Boolean = true,
    val currentTab: MenuOptions = MenuOptions.PROFILE,
    val rightPaneSize: Size =  Size.Zero,
    val userEmail: String = "",
    val username: String = "",
    val userRoles: List<String> = emptyList(),
    val userId: String = "",
    val doctorLicenseNumber: String = "",
    val doctorClinicAddress: String = "",
    val doctorAvailability: String = "",
    val doctorSpecialization: String = "",
    val specializationTextFieldState: TextFieldState = TextFieldState(),
    val licenseNumberTextFieldState: TextFieldState = TextFieldState(),
    val clinicAddressTextFieldState: TextFieldState = TextFieldState(),
    val doctorAvailabilityTextFieldState: TextFieldState = TextFieldState(),
    val canBecomeDoctor: Boolean = false,
    val errorMessage: String = "",
)

enum class MenuOptions {
    PROFILE, BECOME_DOCTOR, BECOME_PATIENT, PATIENT_PROFILE, DOCTOR_PROFILE
}
