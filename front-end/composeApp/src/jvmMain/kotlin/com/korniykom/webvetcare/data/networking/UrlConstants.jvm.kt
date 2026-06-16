package com.korniykom.webvetcare.data.networking

actual val BASE_URL: String = System.getenv("BASE_URL") ?: "http://localhost:8080/api"