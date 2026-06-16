package com.korniykom.webvetcare.domain.util

interface TokenStorage {
    suspend fun getAccessToken(): String?
    suspend fun saveAccessToken(token: String)
    suspend fun clearToken()
}