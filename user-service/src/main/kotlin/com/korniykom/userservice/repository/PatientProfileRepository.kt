package com.korniykom.userservice.repository

import com.korniykom.userservice.entity.PatientProfile
import org.springframework.data.jpa.repository.JpaRepository

interface PatientProfileRepository: JpaRepository<PatientProfile, String> {
}