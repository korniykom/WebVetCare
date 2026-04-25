package com.korniykom.userservice.exception_handling

import com.korniykom.userservice.domain.exceptions.DoctorProfileNotFoundException
import com.korniykom.userservice.domain.exceptions.PatientProfileNotFoundException
import com.korniykom.userservice.domain.exceptions.UserAlreadyDoctorException
import com.korniykom.userservice.domain.exceptions.UserAlreadyPatientException
import com.korniykom.userservice.domain.exceptions.UserNotDoctorException
import com.korniykom.userservice.domain.exceptions.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onUserNotFound(
        e: UserNotFoundException
    ) = mapOf(
        "code" to "USER_NOT_FOUND",
        "message" to e.message
    )

    @ExceptionHandler(UserNotDoctorException::class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    fun onUserNotDoctorException(
        e: UserNotDoctorException
    ) = mapOf(
        "code" to "USER_NOT_A_DOCTOR",
        "message" to e.message
    )

    @ExceptionHandler(DoctorProfileNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onDoctorProfileNotFoundException(
        e: DoctorProfileNotFoundException
    ) = mapOf(
        "code" to "DOCTOR_PROFILE_NOT_FOUND",
        "message" to e.message
    )

    @ExceptionHandler(PatientProfileNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun onPatientProfileNotFoundException(
        e: PatientProfileNotFoundException
    ) = mapOf(
        "code" to "PATIENT_PROFILE_NOT_FOUND",
        "message" to e.message
    )

    @ExceptionHandler(UserAlreadyDoctorException::class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun onUserAlreadyDoctorException(
        e: UserAlreadyDoctorException
    ) = mapOf(
        "code" to "USER_ALREADY_DOCTOR",
        "message" to e.message
    )

    @ExceptionHandler(UserAlreadyPatientException::class)
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    fun onUserAlreadyPatientException(
        e: UserAlreadyPatientException
    ) = mapOf(
        "code" to "USER_ALREADY_PATIENT",
        "message" to e.message
    )


}