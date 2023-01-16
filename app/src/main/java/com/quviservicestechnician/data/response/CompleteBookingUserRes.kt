package com.quviservicestechnician.data.response

data class CompleteBookingUserRes(
    val activated: Int,
    val country_code: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val social_id: String,
    val type: String,
    val user_detail: CompleteBookingUserDetailsRes,
    val verified_social_phone: Int
)