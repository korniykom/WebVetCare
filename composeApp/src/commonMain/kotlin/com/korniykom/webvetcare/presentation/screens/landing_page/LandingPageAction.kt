package com.korniykom.webvetcare.presentation.screens.landing_page

sealed interface LandingPageAction {
    data object LoginButtonClicked
    data object RegisterButtonClicked
}