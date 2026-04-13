package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.korniykom.core.presentation.util.currentDeviceConfiguration
import com.korniykom.core.presentation.util.isMultiPane
import com.korniykom.webvetcare.presentation.navigation.dashboard.DashboardRoute
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier
) {
    val device = currentDeviceConfiguration()

    var isExpanded by remember { mutableStateOf(true) }

    val menuWidth by animateDpAsState(
        targetValue = if (isExpanded) 240.dp else 72.dp,
        label = "menu_width"
    )

    val dashboardBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(DashboardRoute.Home::class, serializer())
                    subclass(DashboardRoute.Patients::class, serializer())
                    subclass(DashboardRoute.Settings::class, serializer())
                }
            }
        },
        DashboardRoute.Home
    )

    if(device.isMultiPane()) {
        Row {
            DashboardMenu(
                modifier = Modifier.width(menuWidth),
                onToggle = { isExpanded = !isExpanded}
            )
            NavDisplay(
                backStack = dashboardBackStack,
                entryProvider = entryProvider {
                    entry<DashboardRoute.Home> {
                        LandingPageScreen(
                            navigateToLoginScreen = {},
                            navigateToRegisterScreen = {}
                        )
                    }
                    entry<DashboardRoute.Settings> {
                        LandingPageScreen(
                            navigateToLoginScreen = {},
                            navigateToRegisterScreen = {}
                        )
                    }
                    entry<DashboardRoute.Patients> {
                        LandingPageScreen(
                            navigateToLoginScreen = {},
                            navigateToRegisterScreen = {}
                        )
                    }
                }

            )
        }
    } else {
        NavDisplay(
            backStack = dashboardBackStack,
            entryProvider = entryProvider {
                entry<DashboardRoute.Home> {
                    LandingPageScreen(
                        navigateToLoginScreen = {},
                        navigateToRegisterScreen = {}
                    )
                }
                entry<DashboardRoute.Settings> {
                    LandingPageScreen(
                        navigateToLoginScreen = {},
                        navigateToRegisterScreen = {}
                    )
                }
                entry<DashboardRoute.Patients> {
                    LandingPageScreen(
                        navigateToLoginScreen = {},
                        navigateToRegisterScreen = {}
                    )
                }
            }
        )
    }



    Text("Dashboard")
}