package com.quviservicestechnician.data.response

data class UserInfoResponse(
    val code: Int,
    val message: String,
    val results: UserInfoDataRes,
    val status: Boolean
)