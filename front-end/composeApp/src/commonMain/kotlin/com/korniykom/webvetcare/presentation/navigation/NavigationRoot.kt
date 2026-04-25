package com.korniykom.webvetcare.presentation.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.korniykom.webvetcare.domain.util.AuthEvent
import com.korniykom.webvetcare.domain.util.AuthEventBus
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardRoute
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardScreen
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageRoot
import com.korniykom.webvetcare.presentation.screens.login.LoginScreenRoot
import com.korniykom.webvetcare.presentation.screens.register.RegisterScreenRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NavigationRoot(
    viewModel: NavigationViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val startDestination = viewModel.startDestination

    val rootBackStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.LandingPage::class, serializer())
                    subclass(Route.RegisterScreen::class, serializer())
                    subclass(Route.LoginScreen::class, serializer())
                    subclass(Route.Dashboard::class, serializer())
                }
            }
        },
        startDestination ?: Route.LandingPage
    )

    LaunchedEffect(Unit) {
        AuthEventBus.events.collect { event ->
            when (event) {
                AuthEvent.SessionExpired -> {
                    rootBackStack.clear()
                    rootBackStack.add(Route.LoginScreen)
                }
            }
        }
    }
    SharedTransitionLayout {

        NavDisplay(
            modifier = modifier,
            backStack = rootBackStack,
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(),
                rememberViewModelStoreNavEntryDecorator()
            ),
            entryProvider = entryProvider {
                entry<Route.LandingPage> {
                    LandingPageRoot(
                        navigateToLoginScreen = {
                            rootBackStack.navigateToLoginScreen()
                        },
                        navigateToRegisterScreen = {
                            rootBackStack.navigateToRegisterScreen()
                        }
                    )
                }
                entry<Route.LoginScreen> {
                    LoginScreenRoot(
                        navigateToRegister = {
                            rootBackStack.navigateToRegisterScreen()
                        },
                        onLoginSuccess = {
                            rootBackStack.navigateToDashboardScreen()
                            rootBackStack.removeAllLoginScreens()
                        }
                    )
                }
                entry<Route.RegisterScreen> {
                    RegisterScreenRoot(
                        navigateToLoginScreen = {
                            rootBackStack.navigateToLoginScreen()
                        },
                        onRegisterSuccess = {
                            rootBackStack.navigateToDashboardScreen()
                            rootBackStack.removeAllRegisterScreens()
                        }
                    )
                }
                entry<Route.Dashboard> {
                    DashboardScreen()
                }
            }
        )
    }
}

fun MutableList<NavKey>.removeAllRegisterScreens() {
    removeAll { it is Route.RegisterScreen }
}

fun MutableList<NavKey>.removeAllLoginScreens() {
    removeAll { it is Route.LoginScreen }
}

fun MutableList<NavKey>.removeAllDashboardScreens() {
    removeAll { it is Route.Dashboard }
}

fun MutableList<NavKey>.removeLandingPage() {
    removeAll { it is Route.LandingPage }
}


fun MutableList<NavKey>.navigateToRegisterScreen() {
    removeAllRegisterScreens()
    add(Route.RegisterScreen)
}

fun MutableList<NavKey>.navigateToLoginScreen() {
    removeAllLoginScreens()
    add(Route.LoginScreen)
}

fun MutableList<NavKey>.navigateToDashboardScreen() {
    removeAllDashboardScreens()
    removeLandingPage()
    removeAllLoginScreens()
    removeAllRegisterScreens()
    add(Route.Dashboard)
}
