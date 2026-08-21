package com.korniykom.userservice.entity

import com.korniykom.user.dto.Role
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    val id: String,
    @Column(nullable = false)
    val email: String,
    @Column(nullable = false)
    val username: String,
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")]
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val roles: MutableSet<Role> = mutableSetOf(Role.USER)
)