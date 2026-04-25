package com.korniykom.auth.controller

import com.korniykom.auth.service.TokenService
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.KeyUse
import com.nimbusds.jose.jwk.RSAKey
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JwksController(
    private val tokenService: TokenService
) {
    @GetMapping("/.well-known/jwks.json")
    fun jwks(): Map<String, Any> {
        val publicKey = tokenService.getPublicKey()
        val rsaKey = RSAKey.Builder(publicKey)
            .keyUse(KeyUse.SIGNATURE)
            .algorithm(JWSAlgorithm.RS256)
            .keyID("auth-service-key")
            .build()

        return JWKSet(rsaKey).toJSONObject()
    }
}