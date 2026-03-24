package com.korniykom.auth.service

import com.korniykom.auth.domain.exceptions.UserAlreadyExistsException
import com.korniykom.auth.domain.type.User
import com.korniykom.auth.dto.LoginRequest
import com.korniykom.auth.dto.TokenResponse
import com.korniykom.auth.entity.UserEntity
import com.korniykom.auth.mappers.toUser
import com.korniykom.auth.repository.AuthUserRepository
import com.korniykom.auth.security.PasswordEncoder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authUserRepository: AuthUserRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun register(email: String, username: String, password: String): User {
        val trimmedEmail = email.trim()
        val user = authUserRepository.findByEmail(email)

        if(user != null) {
            throw UserAlreadyExistsException()
        }

        val savedUser = authUserRepository.saveAndFlush(
            UserEntity(
                id = null,
                email = trimmedEmail,
                username = username,
                password = passwordEncoder.encode(password)
            )
        ).toUser()
        return savedUser

    }

    fun login(request: LoginRequest): Pair<TokenResponse, String> {
        TODO("implement")
    }

    fun refresh(refreshToken: String): Pair<TokenResponse, String> {
        TODO("implement")
    }

    fun logout() {
        TODO("implement")
    }
}