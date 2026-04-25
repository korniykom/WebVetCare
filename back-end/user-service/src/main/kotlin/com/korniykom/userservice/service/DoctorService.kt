package com.korniykom.userservice.service

import com.korniykom.userservice.domain.enums.Role
import com.korniykom.userservice.domain.exceptions.DoctorProfileNotFoundException
import com.korniykom.userservice.domain.exceptions.UserAlreadyDoctorException
import com.korniykom.userservice.domain.exceptions.UserNotFoundException
import com.korniykom.userservice.dto.request.BecomeDoctorRequest
import com.korniykom.userservice.dto.response.DoctorProfileResponse
import com.korniykom.userservice.entity.DoctorProfile
import com.korniykom.userservice.repository.DoctorProfileRepository
import com.korniykom.userservice.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class DoctorService(
    private val userRepository: UserRepository,
    private val doctorProfileRepository: DoctorProfileRepository
) {
    fun becomeDoctor(userId: String, request: BecomeDoctorRequest): DoctorProfileResponse {
        val user = userRepository.findById(userId)
            .orElseThrow { UserNotFoundException() }

        if (user.roles.contains(Role.DOCTOR)) {
            throw UserAlreadyDoctorException()
        }

        user.roles.add(Role.DOCTOR)
        userRepository.save(user)

        val profile = doctorProfileRepository.save(
            DoctorProfile(
                userId = userId,
                specialization = request.specialization,
                licenseNumber = request.licenseNumber,
                clinicAddress = request.clinicAddress,
                availability = request.availability,
            )
        )
        return DoctorProfileResponse(
            id = profile.userId,
            specialization = profile.specialization,
            licenseNumber = request.licenseNumber,
            clinicAddress = request.clinicAddress,
            availability = request.availability,
        )
    }

    fun getDoctorProfile(userId: String): DoctorProfileResponse {

        val profile = doctorProfileRepository.findById(userId)
            .orElseThrow { DoctorProfileNotFoundException() }
        return DoctorProfileResponse(
            id = profile.userId,
            specialization = profile.specialization,
            licenseNumber = profile.licenseNumber,
            clinicAddress = profile.clinicAddress,
            availability = profile.availability
        )
    }
}