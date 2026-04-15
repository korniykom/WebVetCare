package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.webvetcare.domain.user_service.UserService
import com.korniykom.webvetcare.domain.util.TokenStorage
import com.korniykom.webvetcare.domain.util.onFailure
import com.korniykom.webvetcare.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DashboardViewModel(
    private val userService: UserService,
    private val tokenStorage: TokenStorage
) : ViewModel() {
    private var hasLoadedInitialData = false

    private val eventChannel = Channel<DashboardEvent>()
    val events = eventChannel.receiveAsFlow()
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.onStart {
        if (!hasLoadedInitialData) {
            loadDataAboutUser()
            hasLoadedInitialData = true
        }
    }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = DashboardState()
        )

    private fun loadDataAboutUser() {
        viewModelScope.launch {
            val token = tokenStorage.getAccessToken()
            userService.getUserInfo(token!!)
                .onSuccess { response ->
                    _state.update { it.copy(
                        userEmail = response.email,
                        userRoles = response.roles
                    ) }
                }
                .onFailure {
                    //TODO show snackbar about failure
                }
        }
    }

    fun onAction(action: DashboardActions) {
        when (action) {
            DashboardActions.OnGoToBecomeDoctor -> {
                _state.update { it.copy(
                    currentTab = MenuOptions.BECOME_DOCTOR
                ) }
            }
            DashboardActions.OnGoToBecomePatient -> {
                _state.update { it.copy(
                    currentTab = MenuOptions.BECOME_PATIENT
                ) }
            }
            DashboardActions.OnGoToDoctorProfile -> {
                _state.update { it.copy(
                    currentTab = MenuOptions.DOCTOR_PROFILE
                ) }
            }
            DashboardActions.OnGoToPatientProfile -> {
                _state.update { it.copy(
                    currentTab = MenuOptions.PATIENT_PROFILE
                ) }
            }
            DashboardActions.OnGoToProfile -> {
                _state.update { it.copy(
                    currentTab = MenuOptions.PROFILE
                ) }
            }
            DashboardActions.OnToggleMenuExpand -> {
                _state.update {
                    it.copy(
                        isMenuExpanded = !it.isMenuExpanded
                    )
                }
            }
        }
    }
}