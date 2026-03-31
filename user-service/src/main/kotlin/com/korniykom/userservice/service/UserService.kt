package com.korniykom.userservice.service

import com.korniykom.userservice.dto.response.UserResponse
import com.korniykom.userservice.entity.User
import com.korniykom.userservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun getOrCreateUser(id: String, email: String): UserResponse {
        val user = userRepository.findById(id).orElseGet {
            userRepository.save(
                User(
                    id = id,
                    email = email
                )
            )
        }
        return UserResponse(
            id = user.id,
            email = user.email,
            roles = user.roles
        )
    }
}