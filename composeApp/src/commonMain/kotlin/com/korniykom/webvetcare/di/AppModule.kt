package com.korniykom.webvetcare.di

import com.korniykom.webvetcare.data.auth_service.KtorAuthService
import com.korniykom.webvetcare.data.logging.KermitLogger
import com.korniykom.webvetcare.data.networking.HttpClientFactory
import com.korniykom.webvetcare.data.user_service.KtorUserService
import com.korniykom.webvetcare.domain.auth_service.AuthService
import com.korniykom.webvetcare.domain.logging.WebVetCareLogger
import com.korniykom.webvetcare.domain.user_service.UserService
import com.korniykom.webvetcare.presentation.screens.dashboard.DashboardViewModel
import com.korniykom.webvetcare.presentation.screens.login.LoginViewModel
import com.korniykom.webvetcare.presentation.screens.register.RegisterViewModel
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::LoginViewModel)
    viewModelOf(::RegisterViewModel)
    viewModelOf(::DashboardViewModel)
    single<WebVetCareLogger> { KermitLogger }
    single { HttpClientFactory(get(), get()).create(get()) }
    singleOf(::KtorAuthService) bind AuthService::class
    singleOf(::KtorUserService) bind UserService::class
    single<HttpClientEngine> {
        CIO.create()
    }
}


expect val platformModule: Module