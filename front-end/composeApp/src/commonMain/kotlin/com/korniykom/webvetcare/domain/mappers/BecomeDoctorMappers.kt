package com.korniykom.webvetcare.domain.mappers

import com.korniykom.webvetcare.data.dto.BecomeDoctorResponseSerializable
import com.korniykom.webvetcare.domain.user_service.BecomeDoctorResponse

fun BecomeDoctorResponseSerializable.toDomain(): BecomeDoctorResponse {
    return BecomeDoctorResponse(
        id = id,
        specialization = specialization,
        licenseNumber = licenseNumber,
        availability = availability,
        clinicAddress = clinicAddress
    )
}