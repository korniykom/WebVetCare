package com.korniykom.webvetcare

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform