package com.korniykom.webvetcare.domain.user_service


import com.korniykom.webvetcare.data.dto.BecomeDoctorResponseSerializable
import com.korniykom.webvetcare.data.dto.BecomePatientResponseSerializable
import com.korniykom.webvetcare.data.dto.GetUserResponseInfoSerializable
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult

interface UserService {
    suspend fun getUserInfo(
        accessToken: String
    ): NetworkResult<GetUserResponseInfoSerializable, DataError.Remote>

    suspend fun becomeDoctor(
        specialization: String,
        licenseNumber: String,
        clinicAddress: String,
        availability: String
    ): NetworkResult<BecomeDoctorResponseSerializable, DataError.Remote>

    suspend fun becomePatient(
        contactEmail: String,
        contactPhoneNumber: String,
    ): NetworkResult<BecomePatientResponseSerializable, DataError.Remote>
}