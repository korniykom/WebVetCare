package com.korniykom.auth.entity

import com.korniykom.auth.domain.type.UserId
import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.util.*

@Entity
@Table(name = "refresh_tokens")
class RefreshTokenEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID? = null,
    @Column(nullable = false)
    val userId: UserId,
    @Column(nullable = false)
    val tokenHash: String,
    @Column(nullable = false)
    val expiresAt: Instant,
    @CreationTimestamp
    val createdAt: Instant = Instant.now()
)