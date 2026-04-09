package com.korniykom.webvetcare.data.networking

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.korniykom.webvetcare.domain.util.TokenStorage
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import okio.Path.Companion.toPath

class JvmTokenStorage : TokenStorage {
    private val dataStore: DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
        produceFile = {
            "${System.getProperty("user.home")}/.webvetcare/auth.preferences_pb".toPath()
        }
    )
    private val accessTokenKey = stringPreferencesKey("access_token")

    override suspend fun getAccessToken(): String? {
        return dataStore.data.map { it[accessTokenKey] }.first()
    }

    override suspend fun saveAccessToken(token: String) {
        dataStore.edit { it[accessTokenKey] = token }
    }

    override suspend fun clearToken() {
        dataStore.edit { it.remove(accessTokenKey) }
    }
}