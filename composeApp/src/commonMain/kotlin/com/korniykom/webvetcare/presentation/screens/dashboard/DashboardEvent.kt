package com.korniykom.webvetcare.presentation.screens.dashboard

sealed interface DashboardEvent {
    data object NavigateToProfile: DashboardEvent
    data object NavigateToBecomeDoctor: DashboardEvent
    data object NavigateToBecomePatient: DashboardEvent
    data object NavigateToDoctorProfile: DashboardEvent
    data object NavigateToPatientProfile: DashboardEvent
    data object SuccessfullyBecomeDoctor: DashboardEvent
    data object FailedToBecomeDoctor: DashboardEvent

    data object SuccessfullyBecomePatient: DashboardEvent
    data object FailedToBecomePatient: DashboardEvent


}