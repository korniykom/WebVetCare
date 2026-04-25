package com.korniykom.webvetcare.data.logging

import co.touchlab.kermit.Logger
import com.korniykom.webvetcare.domain.logging.WebVetCareLogger


object KermitLogger : WebVetCareLogger {
    override fun info(message: String) {
        Logger.i(message)
    }

    override fun debug(message: String) {
        Logger.d(message)
    }

    override fun warning(message: String) {
        Logger.w(message)

    }

    override fun error(message: String, throwable: Throwable?) {
        Logger.e(message, throwable)
    }
}