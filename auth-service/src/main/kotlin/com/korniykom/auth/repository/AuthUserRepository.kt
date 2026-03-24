package com.korniykom.auth.repository

import com.korniykom.auth.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AuthUserRepository: JpaRepository<UserEntity, UUID> {
    fun findByEmail(email: String): UserEntity?
}