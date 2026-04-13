package com.korniykom.webvetcare.presentation.screens.dashboard.sidemenu

sealed interface DashboardSideMenuActions {
    data object OnGoToProfile: DashboardSideMenuActions
    data object OnGoToBecomeDoctor: DashboardSideMenuActions
    data object OnGoToBecomePatient: DashboardSideMenuActions
    data object onGoToPatientProfile: DashboardSideMenuActions
    data object OnGoToDoctorProfile: DashboardSideMenuActions
}