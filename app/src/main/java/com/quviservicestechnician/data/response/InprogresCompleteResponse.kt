package com.quviservicestechnician.data.response

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class InprogresCompleteResponse(
    val additional_request: String,
    val address_id: Int,
    val booked_date: String,
    val booked_end_time: String,
    val booked_start_time: String,
    val cancel_reason: String,
    val created_at: String,
    val gst_amount: Int,
    val id: Int,
    val listing_id: Int,
    val service: InprogressCompleteServiceRes,
    val status: String,
    val sub_total: Int,
    val technician_id: Int,
    val total_amount: Int,
    val total_hours: String,
    val transaction_id: Int,
    val updated_at: String,
    val user: InprogressUserRes,
    val user_id: Int,
    val vendor: CompleteBookingVendorRes,
    val vendor_id: Int,
) : Serializable