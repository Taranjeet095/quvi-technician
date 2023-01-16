package com.quviservicestechnician.data.response

data class AcceptOrderResponse(
    val code: Int,
    val message: String,
    val results: List<Any>,
    val status: Boolean
)