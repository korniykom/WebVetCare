package com.korniykom.userservice.dto.response

import com.korniykom.userservice.domain.enums.Role

data class UserResponse(
    val id: String,
    val email: String,
    val roles: Set<Role>
)