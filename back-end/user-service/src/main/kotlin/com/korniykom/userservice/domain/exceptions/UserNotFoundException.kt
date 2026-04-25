package com.korniykom.userservice.domain.exceptions

class UserNotFoundException : RuntimeException(
    "User not found"
)