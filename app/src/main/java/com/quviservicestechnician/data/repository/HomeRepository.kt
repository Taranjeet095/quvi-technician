package com.quviservicestechnician.data.repository

import com.quviservicestechnician.data.network.AuthApi

class HomeRepository(
    private val api: AuthApi
): BaseRepository() {

    suspend fun getNewBookingRequest(
    ) = safeApiCall {
        api.newBookingDetails()
    }

    suspend fun acceptBooking(
        booking_id: Int
    ) = safeApiCall {
        api.acceptOrder(booking_id)
    }
}