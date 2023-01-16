package com.quviservicestechnician.data.response

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class CompleteBookingVendorRes(
    val activated: Int,
    val created_at: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val updated_at: String
):Serializable