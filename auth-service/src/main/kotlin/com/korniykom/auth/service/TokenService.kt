package com.korniykom.auth.service

import com.korniykom.auth.config.JwtConfig
import com.korniykom.auth.domain.exceptions.InvalidTokenException
import com.korniykom.auth.domain.type.UserId
import com.korniykom.auth.entity.RefreshTokenEntity
import com.korniykom.auth.repository.RefreshTokenRepository
import com.korniykom.auth.security.TokenGenerator
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm
import org.springframework.security.oauth2.jwt.JwsHeader
import org.springframework.security.oauth2.jwt.JwtClaimsSet
import org.springframework.security.oauth2.jwt.JwtEncoderParameters
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder
import org.springframework.stereotype.Service
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.time.Instant
import java.util.UUID

@Service
class TokenService(
    private val privateKey: RSAPrivateKey,
    private val publicKey: RSAPublicKey,
    private val jwtConfig: JwtConfig,
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    private val encoder = NimbusJwtEncoder(
        ImmutableJWKSet(JWKSet(RSAKey.Builder(publicKey).privateKey(privateKey).build()))
    )

    fun generateAccessToken(userId: UUID, email: String): String {
        val now = Instant.now()
        val claims = JwtClaimsSet.builder()
            .issuer("auth-service")
            .subject(userId.toString())
            .claim("email", email)
            .issuedAt(now)
            .expiresAt(now.plusSeconds(jwtConfig.accessTokenExpiry!!))
            .build()

        return encoder.encode(
            JwtEncoderParameters.from(
                JwsHeader.with(SignatureAlgorithm.RS256).build(),
                claims
            )
        ).tokenValue
    }

    fun generateRefreshToken(userId: UserId): String {
        val token = TokenGenerator.generateSecureToken()
        val hash = DigestUtils.sha256Hex(token)
        val expiresAt = Instant.now().plusSeconds(jwtConfig.refreshTokenExpiry!!)

        refreshTokenRepository.save(
            RefreshTokenEntity(
                userId = userId,
                tokenHash = hash,
                expiresAt = expiresAt
            )
        )

        return token
    }

    fun validateRefreshToken(token: String): UserId {
        val hash = DigestUtils.sha256Hex(token)
        val entity = refreshTokenRepository.findByTokenHash(hash)
            ?: throw InvalidTokenException()

        if (entity.expiresAt.isBefore((Instant.now()))) {
            refreshTokenRepository.delete(entity)
            throw InvalidTokenException()
        }

        refreshTokenRepository.delete(entity)

        return entity.userId
    }

    fun revokeRefreshToken(token: String) {
        val hash = DigestUtils.sha256Hex(token)
        refreshTokenRepository.findByTokenHash(hash)
            ?.let {
                refreshTokenRepository.delete(it)
            }
    }

    fun getPublicKey(): RSAPublicKey = publicKey
}