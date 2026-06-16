package com.korniykom.webvetcare.data.networking

import com.korniykom.webvetcare.domain.util.TokenStorage

class WebTokenStorage : TokenStorage {
    private var accessToken: String? = null

    override suspend fun getAccessToken(): String? = accessToken

    override suspend fun saveAccessToken(token: String) {
        accessToken = token
    }

    override suspend fun clearToken() {
        accessToken = null
    }
}