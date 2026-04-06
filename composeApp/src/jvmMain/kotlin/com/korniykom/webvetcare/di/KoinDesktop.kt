package com.korniykom.webvetcare.di

import org.koin.core.context.GlobalContext.startKoin

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}