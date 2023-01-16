package com.quviservicestechnician.data.response

class AddressBookingResponse(
    val id : Int,
    val user_id: Int,
    val name: String,
    val phone : String,
    val address1 : String,
    val address2 : String,
    val postcode : String,
    val city : String,
    val state : String,
    val country_code: String,
    val country : String,
    val latitude : String,
    val longitude : String,
    val is_dafault: String,
    val created_at: String,
    val updated_at: String
)