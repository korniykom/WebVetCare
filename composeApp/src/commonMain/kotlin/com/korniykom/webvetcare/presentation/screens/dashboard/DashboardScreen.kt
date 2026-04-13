package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.korniykom.webvetcare.presentation.navigation.Route
import com.korniykom.webvetcare.presentation.navigation.dashboard.DashboardRoute
import com.korniykom.webvetcare.presentation.screens.dashboard.sidemenu.DashboardMenu
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier
) {
    val dashboardBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(DashboardRoute.Profile::class, serializer())
                    subclass(DashboardRoute.BecomeDoctor::class, serializer())
                    subclass(DashboardRoute.BecomePatient::class, serializer())
                    subclass(DashboardRoute.DoctorProfile::class, serializer())
                    subclass(DashboardRoute.PatientProfile::class, serializer())

                }
            }
        },
        DashboardRoute.Profile
    )
    Row {
        DashboardMenu()
        NavDisplay(
            backStack = dashboardBackStack,
            entryProvider = entryProvider {
                entry<DashboardRoute.Profile> { LandingPageRoot(
                    navigateToLoginScreen = {  },
                    navigateToRegisterScreen = {}
                ) }
            }
        )
    }
        

}