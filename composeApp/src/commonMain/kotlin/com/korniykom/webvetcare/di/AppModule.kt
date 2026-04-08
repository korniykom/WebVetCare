package com.korniykom.webvetcare.di

import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageViewModel
import com.korniykom.webvetcare.presentation.screens.login.LoginViewModel
import com.korniykom.webvetcare.presentation.screens.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LandingPageViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
}