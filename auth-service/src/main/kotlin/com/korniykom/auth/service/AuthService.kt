package com.korniykom.auth.service

import com.korniykom.auth.domain.exceptions.UserAlreadyExistsException
import com.korniykom.auth.domain.type.User
import com.korniykom.auth.dto.LoginRequest
import com.korniykom.auth.entity.UserEntity
import com.korniykom.auth.mappers.toUser
import com.korniykom.auth.repository.AuthUserRepository
import com.korniykom.auth.security.PasswordEncoder
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authUserRepository: AuthUserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService
) {

    @Transactional
    fun register(email: String, username: String, password: String): Pair<String, String> {
        val trimmedEmail = email.trim()

        if(authUserRepository.findByEmail(trimmedEmail) != null) {
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

        val accessToken = tokenService.generateAccessToken(savedUser.id, savedUser.email)
        val refreshToken = tokenService.generateRefreshToken(savedUser.id)


        return Pair(accessToken,refreshToken)

    }

    fun login(request: LoginRequest): Pair<String, String> {
        TODO("implement")
    }

    fun refresh(refreshToken: String): Pair<String, String> {
        TODO("implement")
    }

    fun logout() {
        TODO("implement")
    }
}