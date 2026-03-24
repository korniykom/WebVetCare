package com.korniykom.auth.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration


@Configuration
@ConfigurationProperties(prefix = "app.jwt")
class JwtConfig {
     var privateKeyPath: String = ""
    var publicKeyPath: String = ""
    var accessTokenExpiry: Long? = null
    var refreshTokenExpiry: Long? = null
}