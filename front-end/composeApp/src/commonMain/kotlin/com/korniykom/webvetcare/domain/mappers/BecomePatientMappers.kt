package com.korniykom.webvetcare.domain.mappers

import com.korniykom.webvetcare.data.dto.BecomePatientResponseSerializable
import com.korniykom.webvetcare.domain.user_service.BecomePatientResponse


fun BecomePatientResponseSerializable.toDomain(): BecomePatientResponse {
    return BecomePatientResponse(
        id = id,
        contactEmail = contactEmail,
        contactPhoneNumber = contactPhoneNumber
    )
}