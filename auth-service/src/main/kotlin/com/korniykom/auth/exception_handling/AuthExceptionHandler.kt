package com.korniykom.auth.exception_handling

import com.korniykom.auth.domain.exceptions.InvalidPasswordException
import com.korniykom.auth.domain.exceptions.InvalidTokenException
import com.korniykom.auth.domain.exceptions.UserAlreadyExistsException
import com.korniykom.auth.domain.exceptions.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AuthExceptionHandler {
    @ExceptionHandler(UserAlreadyExistsException::class)
    @ResponseStatus(HttpStatus.CONFLICT)
    fun onUserAlreadyExists(
        e: UserAlreadyExistsException
    ) = mapOf(
        "code" to "USER_EXISTS",
        "message" to e.message
    )

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun onValidationFailure(
        e: MethodArgumentNotValidException
    ) = mapOf(
        "code" to "VALIDATION_ERROR",
        "fields" to e.bindingResult.fieldErrors.map { error ->
            mapOf(
                "field" to error.field,
                "message" to error.defaultMessage
            )
        }
    )

    @ExceptionHandler(InvalidTokenException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun onInvalidTokenException(
        e: InvalidTokenException
    ) = mapOf(
        "code" to "INVALID_TOKEN",
        "message" to e.message
    )

    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onUserNotFoundException(
        e: UserNotFoundException
    ) = mapOf(
        "code" to "USER_NOT_FOUND",
        "message" to e.message
    )

    @ExceptionHandler(InvalidPasswordException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun onInvalidPasswordException(
        e: InvalidPasswordException
    ) = mapOf(
        "code" to "INVALID_PASSWORD",
        "message" to e.message
    )

}