package com.korniykom.webvetcare.presentation.screens.landing_page

import androidx.lifecycle.ViewModel

class LandingPageViewModel : ViewModel() {
    fun onAction(action: LandingPageAction) {
        when(action) {
            LandingPageAction.LoginButtonClicked -> {}
            LandingPageAction.RegisterButtonClicked -> {}
        }
    }
}