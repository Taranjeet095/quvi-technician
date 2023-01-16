package com.quviservicestechnician.data.repository

import com.quviservicestechnician.data.network.AuthApi
import okhttp3.MultipartBody
import java.io.File

class UserUpdateRepository(
    private val api : AuthApi
): BaseRepository() {

    suspend fun userDetailsUpdate(
        name: String,
        email: String,
        country: String,
        country_code: String,
        phone: String,
//        image: File
    ) = safeApiCall {
        api.userDetailsUpdate(name,email ,country,country_code,phone)
    }
}