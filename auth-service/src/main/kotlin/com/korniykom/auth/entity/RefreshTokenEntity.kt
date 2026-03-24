package com.korniykom.auth.entity

import com.korniykom.auth.domain.type.UserId
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.CreationTimestamp
import java.time.Instant
import java.util.UUID

@Entity
@Table(name = "refresh_tokens")
class RefreshTokenEntity (
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