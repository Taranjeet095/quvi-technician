package com.quviservicestechnician.data.repository

import com.quviservicestechnician.data.network.AuthApi

class BookingRepository(
    private val api: AuthApi
) : BaseRepository() {

    suspend fun getBookings(
    ) = safeApiCall {
        api.bookings()
    }

}