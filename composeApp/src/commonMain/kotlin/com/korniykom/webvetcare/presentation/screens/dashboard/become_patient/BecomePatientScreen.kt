package com.korniykom.webvetcare.presentation.screens.dashboard.become_patient

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldState
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
import com.korniykom.webvetcare.presentation.util.ObserveAsEvents


@Composable
fun BecomePatientScreen(
    viewModel: DashboardViewModel,
    navigateToPatientProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is DashboardEvent.SuccessfullyBecomePatient-> {
                snackbarHostState.show("Successfully become patient!", SnackBarType.SUCCESS)
                viewModel.onAction(DashboardActions.OnFetchDataAboutUser)
                navigateToPatientProfile()
            }
            is DashboardEvent.FailedToBecomePatient -> {
                snackbarHostState.show("Failed to become patient, ${state.value.errorMessage}", type = SnackBarType.ERROR)
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
                headerText = "Become Patient",
                modifier = Modifier
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    TextField(
                        state = viewModel.state.value.patientContactNumberTextFieldState,
                        placeholder = "Enter your contact number",
                        title = "Contact Number",

                        )
                    Spacer(modifier.height(8.dp))
                    TextField(
                        state = viewModel.state.value.patientEmailTextFieldState,
                        placeholder = "Enter your contact email",
                        title = "Contact Email",

                        )

                    Spacer(modifier = Modifier.height(16.dp))
                    WebVetCareButton(
                        text = "Become Patient",
                        style = WebVetCareButtonStyle.TEAL,
                        enabled = state.value.canBecomePatient ,
                        onClick = {viewModel.onAction(DashboardActions.OnBecomePatientClick)}
                    )

                }
            }
        }
    }

}