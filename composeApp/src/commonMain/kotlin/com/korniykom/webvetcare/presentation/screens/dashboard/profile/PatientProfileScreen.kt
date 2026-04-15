package com.korniykom.webvetcare.presentation.screens.dashboard.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.layouts.AdaptiveFormLayout
import com.korniykom.webvetcare.presentation.components.textfields.TextField
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardViewModel


@Composable
fun PatientProfileScreen(
    viewModel: DashboardViewModel,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    Column(
        modifier = modifier
    ) {

        AdaptiveFormLayout(
            headerText = "Profile",
            modifier = Modifier
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    state = TextFieldState(initialText = state.value.userEmail),
                    placeholder = "Enter your email",
                    title = "Email",
                    enabled = false
                )


            }
        }
    }

}