package com.korniykom.gateway

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean

@SpringBootApplication
class GatewayApplication {

    @Bean
    fun routeLocator(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes()
            .route("auth-service") { r ->
                r.path("/api/auth/**")
                    .uri(authServiceUrl)
            }
            .route("user-service") { r ->
                r.path("/api/users/**")
                    .uri(userServiceUrl)
            }
            .route(".well-known/jwks.json") { r ->
                r.path("/.well-knonw/jwks.json")
                    .uri(authServiceUrl)
            }
            .build()
    }

    @Value("\${AUTH_SERVICE_URL:http://localhost:9991}")
    lateinit var authServiceUrl: String

    @Value("\${USER_SERVICE_URL:http://localhost:9992}")
    lateinit var userServiceUrl: String
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}