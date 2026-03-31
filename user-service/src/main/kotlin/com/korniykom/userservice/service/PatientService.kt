package com.korniykom.userservice.service

import com.korniykom.userservice.domain.enums.Role
import com.korniykom.userservice.domain.exceptions.PatientProfileNotFoundException
import com.korniykom.userservice.domain.exceptions.UserAlreadyPatientException
import com.korniykom.userservice.domain.exceptions.UserNotFoundException
import com.korniykom.userservice.dto.request.BecomePatientRequest
import com.korniykom.userservice.dto.response.PatientProfileResponse
import com.korniykom.userservice.entity.PatientProfile
import com.korniykom.userservice.repository.PatientProfileRepository
import com.korniykom.userservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class PatientService(
    private val userRepository: UserRepository,
    private val patientProfileRepository: PatientProfileRepository
) {
    fun becomePatient(userId: String, request: BecomePatientRequest): PatientProfileResponse {
        val user = userRepository.findById(userId)
            .orElseThrow{ UserNotFoundException() }

        if(user.roles.contains(Role.PATIENT)) {
            throw UserAlreadyPatientException()
        }

        user.roles.add(Role.PATIENT)
        userRepository.save(user)

        val profile = patientProfileRepository.save(
            PatientProfile(
                userId = userId,
                contactEmail = request.contact_email
            )
        )

        return PatientProfileResponse(
            id = profile.userId,
            contactEmail = profile.contactEmail
        )
    }

    fun getPatientProfile(userId: String): PatientProfileResponse {

        val profile = patientProfileRepository.findById(userId)
            .orElseThrow { PatientProfileNotFoundException() }

        return PatientProfileResponse(
            id = profile.userId,
            contactEmail = profile.contactEmail
        )
    }
}