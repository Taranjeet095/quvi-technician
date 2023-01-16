package com.quviservicestechnician.data.response

data class NewBookingSubcategoryRes(
    val created_at: String,
    val description: String,
    val id: Int,
    val image: String,
    val name: String,
    val parent_id: String,
    val status: Int,
    val updated_at: String
)