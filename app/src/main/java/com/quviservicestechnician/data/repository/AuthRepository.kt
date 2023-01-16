package com.quviservicestechnician.data.repository

import com.quviservicestechnician.data.UserPreferences
import com.quviservicestechnician.data.network.AuthApi
import com.quviservicestechnician.data.response.UserInfoResponse

class AuthRepository(
    private val api: AuthApi,
    private val userPreferences: UserPreferences,
): BaseRepository() {

    suspend fun login(
        phone: String,
        country_code: String
    ) = safeApiCall {
        api.login(phone,country_code)
    }


    suspend fun saveAuthToken(token: String) {
        userPreferences.saveAccessTokens(token)
    }

    suspend fun putString(key: String, value: String) {
        userPreferences.putString(key, value)
    }

    suspend fun saveUserProfile(value: UserInfoResponse) {
        userPreferences.saveUserProfile(value)
    }

}