package com.korniykom.webvetcare.di

import com.korniykom.webvetcare.data.logging.KermitLogger
import com.korniykom.webvetcare.data.networking.HttpClientFactory
import com.korniykom.webvetcare.domain.logging.WebVetCareLogger
import com.korniykom.webvetcare.domain.util.TokenStorage
import com.korniykom.webvetcare.presentation.screens.landing_page.LandingPageViewModel
import com.korniykom.webvetcare.presentation.screens.login.LoginViewModel
import com.korniykom.webvetcare.presentation.screens.register.RegisterViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LandingPageViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    single<WebVetCareLogger> { KermitLogger }
    single<TokenStorage> { get() }
    single { HttpClientFactory(get(), get()).create(get()) }
}

expect val platformModule: Module