package com.korniykom.webvetcare.presentation.screens.dashboard

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.korniykom.webvetcare.presentation.screens.dashboard.become_doctor.BecomeDoctorScreen
import com.korniykom.webvetcare.presentation.screens.dashboard.become_patient.BecomePatientScreen
import com.korniykom.webvetcare.presentation.screens.dashboard.profile.DoctorProfileScreen
import com.korniykom.webvetcare.presentation.screens.dashboard.profile.PatientProfileScreen
import com.korniykom.webvetcare.presentation.screens.dashboard.profile.ProfileScreen
import com.korniykom.webvetcare.presentation.screens.dashboard.sidemenu.DashboardMenu
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = koinViewModel(),
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
        DashboardMenu(
            viewModel = viewModel,
            onGoToProfile = {
                viewModel.onAction(DashboardActions.OnGoToProfile)
                dashboardBackStack.navigateToProfile()
            },
            onGoToBecomeDoctor = {
                viewModel.onAction(DashboardActions.OnGoToBecomeDoctor)
                dashboardBackStack.navigateToBecomeDoctor()
            },
            onGoToBecomePatient = {
                viewModel.onAction(DashboardActions.OnGoToBecomePatient)
                dashboardBackStack.navigateToBecomePatient()
            },
            onGoToDoctorProfile = {
                viewModel.onAction(DashboardActions.OnGoToDoctorProfile)
                dashboardBackStack.navigateToDoctorProfile()
            },
            onGoToPatientProfile = {
                viewModel.onAction(DashboardActions.OnGoToPatientProfile)
                dashboardBackStack.navigateToPatientProfile()
            },
        )
        NavDisplay(
            backStack = dashboardBackStack,
            entryProvider = entryProvider {
                entry<DashboardRoute.Profile> {
                    ProfileScreen(
                        viewModel = viewModel
                    )
                }
                entry<DashboardRoute.BecomeDoctor> {
                    BecomeDoctorScreen(
                        viewModel = viewModel
                    )
                }
                entry<DashboardRoute.BecomePatient> {
                    BecomePatientScreen(
                        viewModel = viewModel
                    )
                }
                entry<DashboardRoute.DoctorProfile> {
                    DoctorProfileScreen(
                        viewModel = viewModel
                    )
                }
                entry<DashboardRoute.PatientProfile> {
                    PatientProfileScreen(
                        viewModel = viewModel
                    )
                }
            }
        )
    }

}


fun MutableList<NavKey>.navigateToProfile() {
    removeAll { it is DashboardRoute.Profile }
    add(DashboardRoute.Profile)
}

fun MutableList<NavKey>.navigateToBecomeDoctor() {
    removeAll { it is DashboardRoute.BecomeDoctor }
    add(DashboardRoute.BecomeDoctor)
}

fun MutableList<NavKey>.navigateToBecomePatient() {
    removeAll { it is DashboardRoute.BecomePatient }
    add(DashboardRoute.BecomePatient)
}

fun MutableList<NavKey>.navigateToDoctorProfile() {
    removeAll { it is DashboardRoute.DoctorProfile }
    add(DashboardRoute.DoctorProfile)
}

fun MutableList<NavKey>.navigateToPatientProfile() {
    removeAll { it is DashboardRoute.PatientProfile }
    add(DashboardRoute.PatientProfile)
}