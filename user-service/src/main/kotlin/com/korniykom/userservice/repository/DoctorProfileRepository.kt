package com.korniykom.userservice.repository

import com.korniykom.userservice.entity.DoctorProfile
import org.springframework.data.jpa.repository.JpaRepository

interface DoctorProfileRepository: JpaRepository<DoctorProfile, String> {
}