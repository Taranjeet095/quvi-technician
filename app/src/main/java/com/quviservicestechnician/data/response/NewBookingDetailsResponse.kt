package com.quviservicestechnician.data.response
import java.io.Serializable

data class NewBookingDetailsResponse(
    val additional_request: Any,
    val address: AddressBookingResponse,
    val address_id: Any,
    val booked_date: String,
    val booked_end_time: Any,
    val booked_start_time: String,
    val cancel_reason: Any,
    val created_at: String,
    val gst_amount: Int,
    val id: Int,
    val listing_id: Int,
    val service: NewBookingServiceResponse,
    val status: String,
    val sub_total: Int,
    val technician_id: Any,
    val total_amount: Int,
    val total_hours: Any,
    val transaction_id: Int,
    val updated_at: String,
    val user_id: Int,
    val vendor_id: Int
) : Serializable