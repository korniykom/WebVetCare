package com.korniykom.webvetcare.presentation.screens.dashboard.sidemenu

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.korniykom.webvetcare.presentation.components.buttons.GoBackButton
import com.korniykom.webvetcare.presentation.components.list_items.ListItemWithIcon
import com.korniykom.webvetcare.presentation.components.logos.PawLogo
import com.korniykom.webvetcare.presentation.theme.Teal99
import org.koin.compose.viewmodel.koinViewModel
import webvetcare.composeapp.generated.resources.Res
import webvetcare.composeapp.generated.resources.account_circle
import webvetcare.composeapp.generated.resources.paw
import webvetcare.composeapp.generated.resources.settings
import webvetcare.composeapp.generated.resources.stethoscope

@Composable
fun DashboardMenu(
    viewModel: DashboardSideMenuViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    var isExpanded by remember { mutableStateOf(true) }

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
                    AnimatedVisibility(isExpanded) {

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
                    ListItemWithIcon(
                        showOnlyIcon = !isExpanded,
                        icon = Res.drawable.account_circle,
                        leadingText = "Profile",
                        modifier = Modifier
                    )
                    ListItemWithIcon(
                        showOnlyIcon = !isExpanded,
                        icon = Res.drawable.stethoscope,
                        leadingText = "Become Doctor",
                        modifier = Modifier
                    )
                    ListItemWithIcon(
                        showOnlyIcon = !isExpanded,
                        icon = Res.drawable.paw,
                        leadingText = "Become Patient",
                        modifier = Modifier
                    )
                    ListItemWithIcon(
                        showOnlyIcon = !isExpanded,
                        icon = Res.drawable.settings,
                        leadingText = "Patient Profile",
                        modifier = Modifier
                    )
                    ListItemWithIcon(
                        showOnlyIcon = !isExpanded,
                        icon = Res.drawable.settings,
                        leadingText = "Doctor Profile",
                        modifier = Modifier
                    )
                }

            }
            GoBackButton(
                modifier = Modifier
                    .offset(x = (menuWidth))
                    .padding(top = 8.dp)
                    .width(24.dp),
                onClick = { isExpanded = !isExpanded },
                isExpanded = isExpanded,
            )
        }

    }


}