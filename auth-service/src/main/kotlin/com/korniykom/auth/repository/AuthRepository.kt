package com.korniykom.auth.repository

import com.korniykom.auth.entity.AuthUser
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AuthRepository: JpaRepository<AuthUser, UUID> {
    fun findByEmail(email: String): AuthUser?
}