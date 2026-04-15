package com.korniykom.webvetcare.domain.user_service

import com.korniykom.webvetcare.domain.util.DataError
import com.korniykom.webvetcare.domain.util.NetworkResult

interface UserService {
    suspend fun getUserInfo(
        accessToken: String
    ): NetworkResult<GetUserInfoResponse, DataError.Remote>
}