package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.webvetcare.domain.user_service.UserService
import com.korniykom.webvetcare.domain.util.TokenStorage
import com.korniykom.webvetcare.domain.util.onFailure
import com.korniykom.webvetcare.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
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
                    _state.update {
                        it.copy(
                            userId = response.id,
                            userEmail = response.email,
                            userRoles = response.roles,
                            username = response.username
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            errorMessage = error.toString()
                        )
                    }
                }
        }
    }

    private val isSpecializationValidFlow =
        snapshotFlow { state.value.specializationTextFieldState.text.toString() }
            .map { specialization -> specialization.isNotBlank() }
            .distinctUntilChanged()

    private val isLicenseNumberValidFlow =
        snapshotFlow { state.value.licenseNumberTextFieldState.text.toString() }
            .map { licenseNumber -> licenseNumber.isNotBlank() }
            .distinctUntilChanged()

    private val isClinicAddressValidFlow =
        snapshotFlow { state.value.clinicAddressTextFieldState.text.toString() }
            .map { clinicAddress -> clinicAddress.isNotBlank() }
            .distinctUntilChanged()

    private val isAvailabilityValidFlow =
        snapshotFlow { state.value.doctorAvailabilityTextFieldState.text.toString() }
            .map { availability -> availability.isNotBlank() }

    private val isPatientEmailValidFlow =
        snapshotFlow { state.value.patientEmailTextFieldState.text.toString() }
            .map { email -> email.isNotBlank() }
            .distinctUntilChanged()

    private val isPatientPhoneNumberValidFlow =
        snapshotFlow { state.value.patientContactNumberTextFieldState.text.toString() }
            .map { number -> number.isNotBlank() }

    private fun observeBecomePatientTextStates() {
        combine(
            isPatientEmailValidFlow,
            isPatientPhoneNumberValidFlow
        ) { isPatientEmailValid, isPatientPhoneNumberValid ->
            _state.update {
                it.copy(
                    canBecomePatient = isPatientEmailValid && isPatientPhoneNumberValid
                )
            }
        }.launchIn(viewModelScope)
    }

    private fun observeBecomeDoctorTextStates() {
        combine(
            isSpecializationValidFlow,
            isLicenseNumberValidFlow,
            isClinicAddressValidFlow,
            isAvailabilityValidFlow
        ) { isSpecializationValid, isLicenseNumberValid, isClinicAddressValid, isAvailabilityValid ->
            _state.update {
                it.copy(
                    canBecomeDoctor = isSpecializationValid && isLicenseNumberValid && isClinicAddressValid && isAvailabilityValid
                )
            }
        }.launchIn(viewModelScope)
    }
    private fun sendBecomePatientRequest() {
        val email = state.value.patientEmailTextFieldState.text.toString()
        val phoneNumber = state.value.patientContactNumberTextFieldState.text.toString()

        viewModelScope.launch {
            userService.becomePatient(
                contactPhoneNumber = phoneNumber,
                contactEmail = email
            )
                .onSuccess { response ->
                    eventChannel.send(DashboardEvent.SuccessfullyBecomePatient)
                    _state.update { it.copy(
                        canBecomePatient = false,
                        patientEmail = response.contactEmail,
                        patientPhoneNumber = response.contactPhoneNumber
                    ) }
                }
                .onFailure { error ->
                    eventChannel.send(DashboardEvent.FailedToBecomePatient)

                    _state.update {
                        it.copy(
                            errorMessage = error.toString()
                        )
                    }

                }
        }
    }

    private fun sendBecomeDoctorRequest() {
        val specialization = state.value.specializationTextFieldState.text.toString()
        val licenseNumber = state.value.licenseNumberTextFieldState.text.toString()
        val clinicAddress = state.value.clinicAddressTextFieldState.text.toString()
        val availability = state.value.doctorAvailabilityTextFieldState.text.toString()

        viewModelScope.launch {

            userService.becomeDoctor(
                specialization = specialization,
                licenseNumber = licenseNumber,
                clinicAddress = clinicAddress,
                availability = availability
            )
                .onSuccess { response ->
                    eventChannel.send(DashboardEvent.SuccessfullyBecomeDoctor)
                    _state.update {
                        it.copy(
                            doctorSpecialization = response.specialization,
                            doctorClinicAddress = response.clinicAddress,
                            doctorLicenseNumber = response.licenseNumber,
                            doctorAvailability = response.availability,
                            canBecomeDoctor = false,
                        )
                    }
                }
                .onFailure { error ->
                    eventChannel.send(DashboardEvent.FailedToBecomeDoctor)

                    _state.update {
                        it.copy(
                            errorMessage = error.toString()
                        )
                    }
                }
        }
    }

    fun onAction(action: DashboardActions) {
        when (action) {
            DashboardActions.OnGoToBecomeDoctor -> {
                observeBecomeDoctorTextStates()
                _state.update {
                    it.copy(
                        currentTab = MenuOptions.BECOME_DOCTOR
                    )
                }
            }

            DashboardActions.OnGoToBecomePatient -> {
                observeBecomePatientTextStates()
                _state.update {
                    it.copy(
                        currentTab = MenuOptions.BECOME_PATIENT
                    )
                }
            }

            DashboardActions.OnGoToDoctorProfile -> {
                _state.update {
                    it.copy(
                        currentTab = MenuOptions.DOCTOR_PROFILE
                    )
                }
            }

            DashboardActions.OnGoToPatientProfile -> {
                _state.update {
                    it.copy(
                        currentTab = MenuOptions.PATIENT_PROFILE
                    )
                }
            }

            DashboardActions.OnGoToProfile -> {
                _state.update {
                    it.copy(
                        currentTab = MenuOptions.PROFILE
                    )
                }
            }

            DashboardActions.OnToggleMenuExpand -> {
                _state.update {
                    it.copy(
                        isMenuExpanded = !it.isMenuExpanded
                    )
                }
            }

            DashboardActions.OnFetchDataAboutUser -> {
                loadDataAboutUser()
            }

            DashboardActions.OnBecomeDoctorClick -> {
                sendBecomeDoctorRequest()
            }

            DashboardActions.OnBecomePatientClick -> {
                sendBecomePatientRequest()
            }
        }
    }
}