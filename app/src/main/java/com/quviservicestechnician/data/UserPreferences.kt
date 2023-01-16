package com.quviservicestechnician.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.quviservicestechnician.data.response.UserInfoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Akash Singhal on 20-09-2021.
 * Mail Id : akashsi126@gmail.com
 */

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_data_store")

class UserPreferences @Inject constructor(context: Context) {

    private val appContext = context.applicationContext

    val accessToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[ACCESS_TOKEN]
        }

    val refreshToken: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[REFRESH_TOKEN]
        }
    val userInfo: Flow<String?>
        get() = appContext.dataStore.data.map { preferences ->
            preferences[USER_INFO]
        }

    suspend fun saveAccessTokens(accessToken: String) {
        appContext.dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = accessToken
        }
    }



    suspend fun putString(keyValue: String, accessToken: String) {
        val key_value = stringPreferencesKey(keyValue)
        appContext.dataStore.edit { preferences ->
            preferences[key_value] = accessToken
        }
    }

    suspend fun getString(keyValue: String): String {
        val key_value = stringPreferencesKey(keyValue)
        val refreshToken =
            appContext.dataStore.data.map { preferences ->
                preferences[key_value]
            }
        return refreshToken.first().toString()
    }

    suspend fun getInt(keyValue: String): Int {
        val key_value = stringPreferencesKey(keyValue)
        val refreshToken =
            appContext.dataStore.data.map { preferences ->
                preferences[key_value]
            }
        return refreshToken.first().toString() as Int
    }

    suspend fun putInt(keyValue: String, value: Int) {
        val key_value = stringPreferencesKey(keyValue)
        appContext.dataStore.edit { preferences ->
            preferences[key_value] = value.toString()
        }
    }

    suspend fun saveRefreshTokens(refreshToken: String) {
        appContext.dataStore.edit { preferences ->
//            preferences[ACCESS_TOKEN] = accessToken
            preferences[REFRESH_TOKEN] = refreshToken
        }
    }


    suspend fun getUserProfile(): UserInfoResponse {
        val gson = Gson()
        return gson.fromJson(userInfo.first(), UserInfoResponse::class.java)

    }

    suspend fun saveUserProfile(userProfile: UserInfoResponse) {
        val gson = Gson()
        val json = gson.toJson(userProfile)

        appContext.dataStore.edit { preferences ->
            preferences[USER_INFO] = json
        }
    }

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val ACCESS_TOKEN = stringPreferencesKey("key_access_token")
        private val REFRESH_TOKEN = stringPreferencesKey("key_refresh_token")
        private val USER_INFO = stringPreferencesKey("user_info")
    }

}