package com.korniykom.webvetcare.presentation.screens.dashboard.become_doctor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.layouts.AdaptiveFormLayout
import com.korniykom.webvetcare.presentation.components.snackbars.SnackBarType
import com.korniykom.webvetcare.presentation.components.snackbars.WebVetCareSnackBar
import com.korniykom.webvetcare.presentation.components.snackbars.show
import com.korniykom.webvetcare.presentation.components.textfields.TextField
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardActions
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardEvent
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardViewModel
import com.korniykom.webvetcare.presentation.screens.login.LoginEvent
import com.korniykom.webvetcare.presentation.util.ObserveAsEvents


@Composable
fun BecomeDoctorScreen(
    viewModel: DashboardViewModel,
    navigateToDoctorProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is DashboardEvent.SuccessfullyBecomeDoctor -> {
                snackbarHostState.show("Successfully become doctor!", SnackBarType.SUCCESS)
                viewModel.onAction(DashboardActions.OnFetchDataAboutUser)
                navigateToDoctorProfile()
            }
            is DashboardEvent.FailedToBecomeDoctor -> {
                snackbarHostState.show("Failed to become doctor, ${state.value.errorMessage}", type = SnackBarType.ERROR)
            }
            else -> {}
        }
    }

    Scaffold(
        snackbarHost = { WebVetCareSnackBar(snackbarHostState) }
    ) {
        Column(
            modifier = modifier
        ) {

            AdaptiveFormLayout(
                headerText = "Become Doctor",
                modifier = Modifier
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        state = viewModel.state.value.specializationTextFieldState,
                        placeholder = "Enter your specialization",
                        title = "Specialization",

                        )
                    Spacer(modifier.height(8.dp))
                    TextField(
                        state = viewModel.state.value.licenseNumberTextFieldState,
                        placeholder = "Enter Doctor license number",
                        title = "License Number",

                        )
                    Spacer(modifier.height(8.dp))
                    TextField(
                        state = viewModel.state.value.clinicAddressTextFieldState,
                        placeholder = "Enter Doctor clinic address",
                        title = "Clinic Address",

                        )
                    Spacer(modifier.height(8.dp))
                    TextField(
                        state = viewModel.state.value.doctorAvailabilityTextFieldState,
                        placeholder = "Enter Doctor Availability",
                        title = "Availability",

                        )
                    Spacer(modifier = Modifier.height(16.dp))
                    WebVetCareButton(
                        text = "Become Doctor",
                        style = WebVetCareButtonStyle.TEAL,
                        enabled = state.value.canBecomeDoctor ,
                        onClick = {viewModel.onAction(DashboardActions.OnBecomeDoctorClick)}
                    )

                }
            }
        }
    }

}