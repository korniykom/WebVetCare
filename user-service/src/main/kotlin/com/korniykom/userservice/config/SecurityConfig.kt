package com.korniykom.userservice.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Value("\${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private lateinit var jwksUri: String

    @Bean
    fun jwtDecoder(): JwtDecoder {
        return NimbusJwtDecoder.withJwkSetUri(jwksUri).build()
    }

    @Bean
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        return httpSecurity
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/api/users/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
            }
            .oauth2ResourceServer { it.jwt {} }
            .build()
    }
}