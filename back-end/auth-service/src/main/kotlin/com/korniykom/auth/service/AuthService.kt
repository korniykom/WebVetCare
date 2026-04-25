package com.korniykom.auth.service

import com.korniykom.auth.domain.exceptions.InvalidPasswordException
import com.korniykom.auth.domain.exceptions.InvalidTokenException
import com.korniykom.auth.domain.exceptions.UserAlreadyExistsException
import com.korniykom.auth.domain.exceptions.UserNotFoundException
import com.korniykom.auth.entity.UserEntity
import com.korniykom.auth.mappers.toUser
import com.korniykom.auth.repository.AuthUserRepository
import com.korniykom.auth.repository.RefreshTokenRepository
import com.korniykom.auth.security.PasswordEncoder
import jakarta.transaction.Transactional
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.stereotype.Service

@Service
class AuthService(
    private val authUserRepository: AuthUserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val tokenService: TokenService,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    @Transactional
    fun register(email: String, password: String, username: String): Pair<String, String> {
        val trimmedEmail = email.trim()

        if (authUserRepository.findByEmail(trimmedEmail) != null) {
            throw UserAlreadyExistsException()
        }

        val savedUser = authUserRepository.saveAndFlush(
            UserEntity(
                id = null,
                email = trimmedEmail,
                password = passwordEncoder.encode(password),
                username = username
            )
        ).toUser()

        val accessToken = tokenService.generateAccessToken(savedUser.id, savedUser.email, savedUser.username)
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
        val accessToken = tokenService.generateAccessToken(domainUser.id, domainUser.email, domainUser.username)
        val refreshToken = tokenService.generateRefreshToken(domainUser.id)

        return Pair(accessToken, refreshToken)
    }

    fun refresh(refreshToken: String): Pair<String, String> {
        val userId = tokenService.validateRefreshToken(refreshToken)
        val user = authUserRepository.findById(userId).orElseThrow {
            UserNotFoundException()
        }
        val accessToken = tokenService.generateAccessToken(userId, user.email, user.username)
        val refreshToken = tokenService.generateRefreshToken(userId)

        return Pair(accessToken, refreshToken)
    }

    fun logout(refreshToken: String) {
        val hash = DigestUtils.sha256Hex(refreshToken)

        refreshTokenRepository.findByTokenHash(hash)
            ?: throw InvalidTokenException()

        tokenService.revokeRefreshToken(refreshToken)
    }
}