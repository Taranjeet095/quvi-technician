package com.quviservicestechnician.data.repository

import com.quviservicestechnician.data.network.AuthApi

class ServiceRepository(
    private val api : AuthApi
): BaseRepository() {

    suspend fun getService(
    ) = safeApiCall {
        api.serviceList()
    }
}