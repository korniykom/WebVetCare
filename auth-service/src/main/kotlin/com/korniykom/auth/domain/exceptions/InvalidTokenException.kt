package com.korniykom.auth.domain.exceptions

class InvalidTokenException: RuntimeException(
    "Invalid or expired token"
)