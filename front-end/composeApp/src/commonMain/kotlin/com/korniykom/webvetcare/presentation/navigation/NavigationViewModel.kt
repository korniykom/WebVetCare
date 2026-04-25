package com.korniykom.webvetcare.presentation.navigation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korniykom.webvetcare.domain.util.TokenStorage
import kotlinx.coroutines.launch

class NavigationViewModel(
    private val tokenStorage: TokenStorage
): ViewModel() {
    var startDestination by mutableStateOf<Route?>(null)
        private set

    init {
        viewModelScope.launch {
            startDestination = if (tokenStorage.getAccessToken() != null) {
                Route.Dashboard
            } else {
                Route.LandingPage
            }
        }
    }
}