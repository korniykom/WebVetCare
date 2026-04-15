package com.korniykom.webvetcare.data.user_service

import com.korniykom.webvetcare.data.dto.GetUserResponseInfoSerializable
import com.korniykom.webvetcare.data.networking.get
import com.korniykom.webvetcare.domain.mappers.toDomain
import com.korniykom.webvetcare.domain.user_service.GetUserInfoResponse
import com.korniykom.webvetcare.domain.user_service.UserService
import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult
import com.korniykom.webvetcare.domain.util.map
import io.ktor.client.HttpClient

class KtorUserService(
    private val httpClient: HttpClient,
) : UserService {
    override suspend fun getUserInfo(
        accessToken: String
    ): NetworkResult<GetUserInfoResponse, DataError.Remote> {
        return httpClient.get<GetUserResponseInfoSerializable>(
            route = "/users/me",

        ).map { response ->
            response.toDomain()
        }
    }

}