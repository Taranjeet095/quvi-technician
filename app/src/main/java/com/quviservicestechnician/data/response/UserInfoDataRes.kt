package com.quviservicestechnician.data.response

data class UserInfoDataRes(
    val active: Int,
    val country: Any,
    val country_code: String,
    val email: Any,
    val id: Int,
    val image: Any,
    val name: String?="",
    val phone: String,
    val technicianJobDone: Int,
    val technicianRating: Int,
    val token: String
)