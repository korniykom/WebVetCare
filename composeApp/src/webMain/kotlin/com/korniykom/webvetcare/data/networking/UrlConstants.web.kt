package com.korniykom.webvetcare.data.networking

import kotlin.js.ExperimentalWasmJsInterop
import kotlin.js.js

@OptIn(ExperimentalWasmJsInterop::class)
actual val BASE_URL: String = js("process.env.BASE_URL")