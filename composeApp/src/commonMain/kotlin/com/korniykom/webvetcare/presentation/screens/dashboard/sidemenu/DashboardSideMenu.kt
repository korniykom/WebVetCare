package com.korniykom.webvetcare.presentation.screens.dashboard.sidemenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.buttons.GoBackButton
import com.korniykom.webvetcare.presentation.components.list_items.ListItemWithIcon
import com.korniykom.webvetcare.presentation.components.logos.PawLogo
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardActions
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardViewModel
import com.korniykom.webvetcare.presentation.screens.dashboard.MenuOptions
import com.korniykom.webvetcare.presentation.theme.Teal99
import webvetcare.composeapp.generated.resources.Res
import webvetcare.composeapp.generated.resources.account_circle
import webvetcare.composeapp.generated.resources.paw
import webvetcare.composeapp.generated.resources.settings
import webvetcare.composeapp.generated.resources.stethoscope

@Composable
fun DashboardMenu(
    viewModel: DashboardViewModel,
    onGoToProfile: () -> Unit,
    onGoToBecomeDoctor: () -> Unit,
    onGoToBecomePatient: () -> Unit,
    onGoToDoctorProfile: () -> Unit,
    onGoToPatientProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    val isExpanded = state.value.isMenuExpanded

    val menuWidth by animateDpAsState(
        targetValue = if (isExpanded) 240.dp else 72.dp,
        label = "menu_width"
    )

    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxHeight()
            .widthIn(min = menuWidth)
            .zIndex(1f)
    ) {
        Box(
            modifier = modifier
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)

            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    PawLogo(
                        imagePadding = 8.dp,
                        imageSize = 24.dp,
                        boxCornerSize = 12.dp,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                    AnimatedVisibility(
                        visible = isExpanded,
                        enter = fadeIn() + expandHorizontally(),
                        exit = fadeOut() + shrinkHorizontally()
                    ) {

                        Text(
                            text = "WebVetCare",
                            style = MaterialTheme.typography.headlineSmall,
                            color = Teal99,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 20.dp, end = 16.dp)
                        )
                    }


                }
                Column(modifier = Modifier.padding(top = 16.dp)) {
                    if(isExpanded) {
                        DashboardSideMenuDivider(
                            text = "USER",
                        )
                    }
                    ListItemWithIcon(
                        showOnlyIcon = !isExpanded,
                        icon = Res.drawable.account_circle,
                        leadingText = "Profile",
                        isSelected = (state.value.currentTab == MenuOptions.PROFILE),
                        onClick = {
                            onGoToProfile()
                        },
                    )
                    if(isExpanded) {
                        DashboardSideMenuDivider(
                            text = "PATIENT",
                        )
                    }
                    if(!state.value.userRoles.contains("PATIENT")) {
                        ListItemWithIcon(
                            showOnlyIcon = !isExpanded,
                            icon = Res.drawable.paw,
                            leadingText = "Become Patient",
                            isSelected = (state.value.currentTab == MenuOptions.BECOME_PATIENT),
                            onClick = {
                                onGoToBecomePatient()
                            },
                        )
                    }
                    if (state.value.userRoles.contains("PATIENT")) {
                        ListItemWithIcon(
                            showOnlyIcon = !isExpanded,
                            icon = Res.drawable.settings,
                            leadingText = "Patient Profile",
                            isSelected = (state.value.currentTab == MenuOptions.PATIENT_PROFILE),
                            onClick = {
                                onGoToPatientProfile()
                            },
                        )
                    }
                    if(isExpanded) {
                        DashboardSideMenuDivider(
                            text = "DOCTOR",
                        )
                    }
                    if(!state.value.userRoles.contains("DOCTOR")) {
                        ListItemWithIcon(
                            showOnlyIcon = !isExpanded,
                            icon = Res.drawable.stethoscope,
                            leadingText = "Become Doctor",
                            isSelected = (state.value.currentTab == MenuOptions.BECOME_DOCTOR),
                            onClick = {
                                onGoToBecomeDoctor()
                            },
                        )
                    }
                    if (state.value.userRoles.contains("DOCTOR")) {
                        ListItemWithIcon(
                            showOnlyIcon = !isExpanded,
                            icon = Res.drawable.settings,
                            leadingText = "Doctor Profile",
                            isSelected = (state.value.currentTab == MenuOptions.DOCTOR_PROFILE),
                            onClick = {
                                onGoToDoctorProfile()
                            },
                        )
                    }
                }

            }
            GoBackButton(
                modifier = Modifier
                    .offset(x = (menuWidth))
                    .padding(top = 8.dp)
                    .width(24.dp),
                onClick = { viewModel.onAction(DashboardActions.OnToggleMenuExpand) },
                isExpanded = isExpanded,
            )
        }

    }


}

@Composable
private fun DashboardSideMenuDivider(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = .7f)
        )
    }
}