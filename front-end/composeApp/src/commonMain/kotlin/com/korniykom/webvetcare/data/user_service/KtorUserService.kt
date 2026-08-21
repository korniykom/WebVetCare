package com.korniykom.webvetcare.data.user_service

import com.korniykom.webvetcare.data.dto.*
import com.korniykom.webvetcare.data.networking.get
import com.korniykom.webvetcare.data.networking.post
import com.korniykom.webvetcare.domain.user_service.UserService
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult
import io.ktor.client.*

class KtorUserService(
    private val httpClient: HttpClient,
) : UserService {
    override suspend fun getUserInfo(
        accessToken: String
    ): NetworkResult<GetUserResponseInfoSerializable, DataError.Remote> {
        return httpClient.get<GetUserResponseInfoSerializable>(
            route = "/users/me",

            )
    }

    override suspend fun becomeDoctor(
        specialization: String,
        licenseNumber: String,
        clinicAddress: String,
        availability: String
    ): NetworkResult<BecomeDoctorResponseSerializable, DataError.Remote> {
        return httpClient.post<BecomeDoctorRequestSerializable, BecomeDoctorResponseSerializable>(
            route = "/users/become-doctor",
            body = BecomeDoctorRequestSerializable(
                specialization = specialization,
                licenseNumber = licenseNumber,
                clinicAddress = clinicAddress,
                availability = availability
            )
        )

    }

    override suspend fun becomePatient(
        contactEmail: String,
        contactPhoneNumber: String
    ): NetworkResult<BecomePatientResponseSerializable, DataError.Remote> {
        return httpClient.post<BecomePatientRequestSerializable, BecomePatientResponseSerializable>(
            route = "/users/become-patient",
            body = BecomePatientRequestSerializable(
                contactEmail = contactEmail,
                contactPhoneNumber = contactPhoneNumber
            )
        )

    }

}