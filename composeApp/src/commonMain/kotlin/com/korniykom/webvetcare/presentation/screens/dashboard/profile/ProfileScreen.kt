package com.korniykom.webvetcare.presentation.screens.dashboard.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.korniykom.webvetcare.presentation.components.chips.RoleChip
import com.korniykom.webvetcare.presentation.components.layouts.AdaptiveFormLayout
import com.korniykom.webvetcare.presentation.components.textfields.TextField
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardActions
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardViewModel

@Composable
fun ProfileScreen(
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
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                state.value.userRoles.forEach { role ->
                    RoleChip(text = role)
                }
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    state = TextFieldState(initialText = state.value.userEmail),
                    placeholder = "Your email",
                    title = "Email",
                    enabled = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    state = TextFieldState(initialText = state.value.username),
                    placeholder = "Your username",
                    title = "Username",
                    enabled = false
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    state = TextFieldState(initialText = state.value.userId),
                    placeholder = "Your ID",
                    title = "User ID",
                    enabled = false
                )
                Spacer(modifier = Modifier.height(16.dp))
                WebVetCareButton(
                    text = "Fetch data from server",
                    style = WebVetCareButtonStyle.TEAL,
                    onClick = { viewModel.onAction(DashboardActions.OnFetchDataAboutUser) }
                )
            }
        }
    }

}