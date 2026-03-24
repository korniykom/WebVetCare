package com.korniykom.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.File
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@Configuration
@ConfigurationProperties(prefix = "app.jwt")
class JwtConfig {
    lateinit var privateKeyPath: String
    lateinit var publicKeyPath: String
    val accessTokenExpiry: Long? = null
    var refreshTokenExpiry: Long? = null
}

@Configuration
class RsaKeyConfig(private val jwtConfig: JwtConfig) {
    @Bean
    fun rsaPrivateKey(): RSAPrivateKey {
        val pem = File(jwtConfig.privateKeyPath).readText()
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\\s".toRegex(), "")
        val decoded = Base64.getDecoder().decode(pem)
        val keySpec = PKCS8EncodedKeySpec(decoded)
        return KeyFactory.getInstance("RSA").generatePrivate(keySpec) as RSAPrivateKey
    }
    @Bean
    fun rsaPublicKey(): RSAPublicKey {
        val pem = File(jwtConfig.publicKeyPath).readText()
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replace("\\s".toRegex(), "")
        val decoded = Base64.getDecoder().decode(pem)
        val keySpec = X509EncodedKeySpec(decoded)
        return KeyFactory.getInstance("RSA").generatePublic(keySpec) as RSAPublicKey
    }
}
