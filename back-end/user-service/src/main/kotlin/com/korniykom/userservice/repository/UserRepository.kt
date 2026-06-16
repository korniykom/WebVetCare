package com.korniykom.userservice.repository

import com.korniykom.userservice.entity.User
import org.springframework.data.jpa.repository.JpaRepository


interface UserRepository : JpaRepository<User, String> {
}