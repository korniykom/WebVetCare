package com.korniykom.webvetcare.di

import com.korniykom.webvetcare.data.networking.JvmTokenStorage
import com.korniykom.webvetcare.domain.util.TokenStorage
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

actual val platformModule = module {
    single<TokenStorage> { JvmTokenStorage() }


}