package com.korniykom.gateway.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {

    @Bean
    fun securityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .authorizeExchange {
                it.pathMatchers("/api/auth/**").permitAll()
                it.anyExchange().authenticated()
            }
            .oauth2ResourceServer {
                it.jwt { }
            }
            .build()
    }
}