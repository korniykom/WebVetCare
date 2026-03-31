package com.korniykom.userservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.MapsId
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "doctor_profiles")
class DoctorProfile(

    @Id
    @Column(name = "user_id")
    val userId: String,

    @Column(nullable = false)
    val specialization: String
)