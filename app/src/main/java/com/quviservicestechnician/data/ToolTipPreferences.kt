package com.quviservicestechnician.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Akash Singhal on 20-09-2021.
 * Mail Id : akashsi126@gmail.com
 */

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "tooltip_data_store")

class ToolTipPreferences @Inject constructor(context: Context) {

    private val appContext = context.applicationContext

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

    suspend fun clear() {
        appContext.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}