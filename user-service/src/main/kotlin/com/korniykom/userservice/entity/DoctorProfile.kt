package com.korniykom.userservice.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "doctor_profiles")
class DoctorProfile(

    @Id
    @Column(name = "user_id")
    val userId: String,

    @Column(nullable = false)
    val specialization: String
)