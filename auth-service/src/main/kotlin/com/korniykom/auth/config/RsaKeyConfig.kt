package com.korniykom.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ResourceLoader
import java.io.File
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.Base64

@Configuration
class RsaKeyConfig(
    private val jwtConfig: JwtConfig,
    private val resourceLoader: ResourceLoader
) {

    @Bean
    fun rsaPrivateKey(): RSAPrivateKey {
        val pem = resourceLoader.getResource(jwtConfig.privateKeyPath)
            .inputStream.bufferedReader().readText()
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\\s".toRegex(), "")
        val decoded = Base64.getDecoder().decode(pem)
        return KeyFactory.getInstance("RSA")
            .generatePrivate(PKCS8EncodedKeySpec(decoded)) as RSAPrivateKey
    }

    @Bean
    fun rsaPublicKey(): RSAPublicKey {
        val pem = resourceLoader.getResource(jwtConfig.publicKeyPath)
            .inputStream.bufferedReader().readText()
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replace("\\s".toRegex(), "")
        val decoded = Base64.getDecoder().decode(pem)
        return KeyFactory.getInstance("RSA")
            .generatePublic(X509EncodedKeySpec(decoded)) as RSAPublicKey
    }
}
