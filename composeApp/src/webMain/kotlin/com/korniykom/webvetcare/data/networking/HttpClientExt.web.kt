package com.korniykom.webvetcare.data.networking

import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive
import kotlin.coroutines.coroutineContext


actual suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> NetworkResult<T, DataError.Remote>
): NetworkResult<T, DataError.Remote> {
    return try {
        val response = execute()
        handleResponse(response)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        if (e.message?.contains("Failed to fetch", ignoreCase = true) == true ||
            e.message?.contains("NetworkError", ignoreCase = true) == true) {
            NetworkResult.Failure(DataError.Remote.NO_INTERNET)
        } else {
            NetworkResult.Failure(DataError.Remote.UNKNOWN)
        }
    }
}