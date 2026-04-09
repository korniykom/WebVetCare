package com.korniykom.webvetcare.domain.util

sealed interface NetworkResult<out D, out E : Error> {
    data class Success<out D>(val data: D) : NetworkResult<D, Nothing>
    data class Failure<out E : Error>(val error: E) : NetworkResult<Nothing, E>
}

inline fun <T, E : Error, R> NetworkResult<T, E>.map(map: (T) -> R): NetworkResult<R, E> {
    return when (this) {
        is NetworkResult.Failure -> NetworkResult.Failure(error)
        is NetworkResult.Success -> NetworkResult.Success(map(data))
    }
}

inline fun <T, E : Error> NetworkResult<T, E>.onSuccess(action: (T) -> Unit): NetworkResult<T, E> {
    return when (this) {
        is NetworkResult.Failure -> this
        is NetworkResult.Success -> {
            action(this.data)
            this
        }
    }
}

inline fun <T, E : Error> NetworkResult<T, E>.onFailure(action: (E) -> Unit): NetworkResult<T, E> {
    return when (this) {
        is NetworkResult.Failure -> {
            action(this.error)
            this
        }

        is NetworkResult.Success -> this
    }
}

fun <T, E : Error> NetworkResult<T, E>.asEmptyResult(): EmptyResult<E> {
    return map { }
}

typealias EmptyResult<E> = NetworkResult<Unit, E>