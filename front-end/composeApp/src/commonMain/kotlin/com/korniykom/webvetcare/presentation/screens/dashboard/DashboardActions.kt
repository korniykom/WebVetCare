package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.compose.ui.geometry.Size

sealed interface DashboardActions {
    data object OnGoToProfile: DashboardActions
    data object OnGoToBecomeDoctor: DashboardActions
    data object OnGoToBecomePatient: DashboardActions
    data object OnGoToPatientProfile: DashboardActions
    data object OnGoToDoctorProfile: DashboardActions
    data object OnToggleMenuExpand: DashboardActions
    data object OnFetchDataAboutUser: DashboardActions
    data object OnBecomeDoctorClick: DashboardActions
    data object OnBecomePatientClick: DashboardActions
}