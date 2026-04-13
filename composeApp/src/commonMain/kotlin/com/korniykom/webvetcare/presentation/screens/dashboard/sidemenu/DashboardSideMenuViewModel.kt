package com.korniykom.webvetcare.presentation.screens.dashboard.sidemenu

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class DashboardSideMenuViewModel: ViewModel() {
    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(DashboardSideMenuState())
    val state = _state.onStart {
        if (!hasLoadedInitialData) {
            /// Initial data loading
            hasLoadedInitialData = true
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = DashboardSideMenuState()
        )

    fun onAction(action: DashboardSideMenuActions) {
        when(action) {
            DashboardSideMenuActions.OnGoToBecomeDoctor -> {}
            DashboardSideMenuActions.OnGoToBecomePatient -> {}
            DashboardSideMenuActions.OnGoToDoctorProfile -> {}
            DashboardSideMenuActions.onGoToPatientProfile -> {}
            DashboardSideMenuActions.OnGoToProfile -> {}
        }
    }
}