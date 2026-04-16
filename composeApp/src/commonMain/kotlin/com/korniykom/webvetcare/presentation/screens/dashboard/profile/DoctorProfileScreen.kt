package com.korniykom.webvetcare.presentation.screens.dashboard.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButton
import com.korniykom.webvetcare.presentation.components.buttons.WebVetCareButtonStyle
import com.korniykom.webvetcare.presentation.components.layouts.AdaptiveFormLayout
import com.korniykom.webvetcare.presentation.components.textfields.TextField
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardActions
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardViewModel


@Composable
fun DoctorProfileScreen(
    viewModel: DashboardViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {

        AdaptiveFormLayout(
            headerText = "Doctor Profile",
            modifier = Modifier
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    state = viewModel.state.value.specializationTextFieldState,
                    placeholder = "Enter your specialization",
                    title = "Specialization",
                    enabled = false,

                    )
                Spacer(modifier.height(8.dp))
                TextField(
                    state = viewModel.state.value.licenseNumberTextFieldState,
                    placeholder = "Enter Doctor license number",
                    title = "License Number",
                    enabled = false,
                    )
                Spacer(modifier.height(8.dp))
                TextField(
                    state = viewModel.state.value.clinicAddressTextFieldState,
                    placeholder = "Enter Doctor clinic address",
                    title = "Clinic Address",
                    enabled = false,
                    )
                Spacer(modifier.height(8.dp))
                TextField(
                    state = viewModel.state.value.doctorAvailabilityTextFieldState,
                    placeholder = "Enter Doctor Availability",
                    title = "Availability",
                    enabled = false,
                    )

            }
        }
    }

}