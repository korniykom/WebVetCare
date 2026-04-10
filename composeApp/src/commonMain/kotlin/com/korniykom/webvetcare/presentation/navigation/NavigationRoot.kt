package com.korniykom.webvetcare.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardScreen
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageRoot
import com.korniykom.webvetcare.presentation.screens.login.LoginScreenRoot
import com.korniykom.webvetcare.presentation.screens.register.RegisterScreenRoot
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.serializer

@Composable
fun NavigationRoot(
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(Route.LandingPage::class, serializer())
                    subclass(Route.RegisterScreen::class, serializer())
                    subclass(Route.LoginScreen::class, serializer())
                }
            }
        },
        Route.LandingPage
    )
    NavDisplay(
        backStack = backStack,
        entryProvider = { key ->
            when (key) {
                is Route.LandingPage -> {
                    NavEntry(key) {
                        LandingPageRoot(
                            navigateToLoginScreen = {
                                backStack.navigateToLoginScreen()
                            },
                            navigateToRegisterScreen = {
                                backStack.navigateToRegisterScreen()
                            }
                        )
                    }
                }

                is Route.LoginScreen -> {
                    NavEntry(key) {
                        LoginScreenRoot(
                            navigateToRegister = {
                                backStack.navigateToRegisterScreen()
                            }
                        )
                    }
                }

                is Route.RegisterScreen -> {
                    NavEntry(key) {
                        RegisterScreenRoot(
                            navigateToLoginScreen = {
                                backStack.navigateToLoginScreen()
                            },
                            onRegisterSuccess = {
                                backStack.navigateToDashboardScreen()
                                backStack.removeAllRegisterScreens()
                            }
                        )
                    }
                }

                is Route.Dashboard -> {
                    NavEntry(key) {
                        DashboardScreen()
                    }
                }

                else -> error("Unknown nav key $key")
            }
        }
    )
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
    add(Route.Dashboard)
}