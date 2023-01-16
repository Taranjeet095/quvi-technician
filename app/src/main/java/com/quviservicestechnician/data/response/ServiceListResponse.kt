package com.quviservicestechnician.data.response

data class ServiceListResponse(
    val code: Int,
    val message: String,
    val results: List<ServiceListDataRes>,
    val status: Boolean
)