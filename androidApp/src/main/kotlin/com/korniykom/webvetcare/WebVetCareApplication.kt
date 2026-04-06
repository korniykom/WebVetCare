package com.korniykom.webvetcare

import android.app.Application
import com.korniykom.webvetcare.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class WebVetCareApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@WebVetCareApplication)
            modules(appModule)
        }
     }
}