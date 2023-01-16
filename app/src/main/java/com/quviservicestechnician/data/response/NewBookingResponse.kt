package com.quviservicestechnician.data.response

data class NewBookingResponse(
    val code: Int,
    val message: String,
    val results: List<NewBookingDetailsResponse>,
    val status: Boolean
)