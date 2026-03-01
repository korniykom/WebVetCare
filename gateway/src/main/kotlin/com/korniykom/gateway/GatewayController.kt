package com.korniykom.gateway

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class GatewayController {
    @GetMapping("/hello")
    fun hello(): String {
        return "hello"
    }
}