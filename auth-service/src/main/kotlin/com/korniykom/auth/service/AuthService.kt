package com.korniykom.auth.service

import com.korniykom.auth.domain.exceptions.InvalidPasswordException
import com.korniykom.auth.domain.exceptions.UserAlreadyExistsException
import com.korniykom.auth.domain.exceptions.UserNotFoundException
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

        if (authUserRepository.findByEmail(trimmedEmail) != null) {
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


        return Pair(accessToken, refreshToken)
    }

    fun login(email: String, password: String): Pair<String, String> {
        val user = authUserRepository.findByEmail(email.trim())
            ?: throw UserNotFoundException()

        if (!passwordEncoder.matches(password, user.password)) {
            throw InvalidPasswordException()
        }

        val domainUser = user.toUser()
        val accessToken = tokenService.generateAccessToken(domainUser.id, domainUser.email)
        val refreshToken = tokenService.generateRefreshToken(domainUser.id)

        return Pair(accessToken, refreshToken)
    }

    fun refresh(refreshToken: String): Pair<String, String> {
        TODO("implement")
    }

    fun logout() {
        TODO("implement")
    }
}