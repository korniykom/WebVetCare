package com.korniykom.webvetcare.di

import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LandingPageViewModel)
}