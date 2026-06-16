package com.korniykom.webvetcare.di

import com.korniykom.webvetcare.data.networking.WebTokenStorage
import com.korniykom.webvetcare.domain.util.TokenStorage
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.JsClient
import org.koin.dsl.module

actual val platformModule = module {
    single<TokenStorage> { WebTokenStorage() }
    single<HttpClientEngine> { JsClient().create() }
}