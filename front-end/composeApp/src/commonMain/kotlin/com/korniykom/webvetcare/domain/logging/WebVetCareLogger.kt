package com.korniykom.webvetcare.domain.logging

interface WebVetCareLogger {
    fun info(message: String)
    fun debug(message: String)
    fun warning(message: String)
    fun error(message: String, throwable: Throwable? = null)
}