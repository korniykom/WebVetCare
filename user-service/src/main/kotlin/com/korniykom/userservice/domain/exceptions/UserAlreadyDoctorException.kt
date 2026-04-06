package com.korniykom.userservice.domain.exceptions

class UserAlreadyDoctorException : RuntimeException(
    "User is already a doctor"
) {
}