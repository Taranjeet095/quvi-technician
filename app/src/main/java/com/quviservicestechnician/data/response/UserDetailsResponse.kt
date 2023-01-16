package com.quviservicestechnician.data.response

data class UserDetailsResponse(
    val activated: Int,
    val country_code: String,
    val email: String,
    val id: Int,
    val name: String,
    val phone: String,
    val social_id: Any,
    val type: String,
    val user_detail: UserDetail,
    val verified_social_phone: Int
)