package com.korniykom.auth.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import java.io.InputStream
import java.security.KeyFactory
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import java.util.*

@Configuration
class RsaKeyConfig(
    private val jwtConfig: JwtConfig
) {

    private fun loadResource(path: String): InputStream {
        return if (path.startsWith("classpath:")) {
            ClassPathResource(path.removePrefix("classpath:")).inputStream
        } else {
            FileSystemResource(path).inputStream
        }
    }

    @Bean
    fun rsaPrivateKey(): RSAPrivateKey {
        val pem = loadResource(jwtConfig.privateKeyPath)
            .bufferedReader().readText()
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("\\s".toRegex(), "")
        val decoded = Base64.getDecoder().decode(pem)
        return KeyFactory.getInstance("RSA")
            .generatePrivate(PKCS8EncodedKeySpec(decoded)) as RSAPrivateKey
    }

    @Bean
    fun rsaPublicKey(): RSAPublicKey {
        val pem = loadResource(jwtConfig.publicKeyPath)
            .bufferedReader().readText()
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "")
            .replace("\\s".toRegex(), "")
        val decoded = Base64.getDecoder().decode(pem)
        return KeyFactory.getInstance("RSA")
            .generatePublic(X509EncodedKeySpec(decoded)) as RSAPublicKey
    }
}