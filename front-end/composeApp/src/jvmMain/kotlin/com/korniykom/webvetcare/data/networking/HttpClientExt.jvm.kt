package com.korniykom.webvetcare.data.networking

import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.ensureActive

actual suspend fun <T> platformSafeCall(
    execute: suspend () -> HttpResponse,
    handleResponse: suspend (HttpResponse) -> NetworkResult<T, DataError.Remote>
): NetworkResult<T, DataError.Remote> {
    return try {
        val response = execute()
        handleResponse(response)
    } catch (e: UnresolvedAddressException) {
        NetworkResult.Failure(DataError.Remote.NO_INTERNET)
    } catch (e: Exception) {
        currentCoroutineContext().ensureActive()
        NetworkResult.Failure(DataError.Remote.UNKNOWN)
    }
}