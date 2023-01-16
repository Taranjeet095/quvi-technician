package com.quviservicestechnician.data.response

data class NewBookingServiceResponse(
    val category: NewBookingCategoryRes,
    val category_id: Int,
    val created_at: String,
    val description: String,
    val id: Int,
    val image: String,
    val listing_type: String,
    val name: String,
    val price: Int,
    val sale_price: Int,
    val status: Int,
    val subcategory: NewBookingSubcategoryRes,
    val subcategory_id: Int,
    val updated_at: String,
    val vendor_id: Int,
    val user: UserDetailsResponse
)