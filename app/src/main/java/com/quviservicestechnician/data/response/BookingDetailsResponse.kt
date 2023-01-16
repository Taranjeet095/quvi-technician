package com.quviservicestechnician.data.response

data class BookingDetailsResponse(
    val code: Int,
    val message: String,
    val results: BookingResultsResponse,
    val status: Boolean
)