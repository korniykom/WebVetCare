package com.korniykom.webvetcare.data.networking

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js

@OptIn(ExperimentalWasmJsInterop::class)
private fun getBaseUrl(): String = js("process.env.BASE_URL || 'http://localhost:8080/api'")

actual val BASE_URL: String = getBaseUrl()