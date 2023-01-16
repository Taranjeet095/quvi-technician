package com.quviservicestechnician.data.repository

import com.quviservicestechnician.data.response.CompleteBookingCategoryRes

class PendingBookingResponse(
    val category: CompleteBookingCategoryRes,
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
    val subcategory: CompleteBookingCategoryRes,
    val subcategory_id: Int,
    val updated_at: String,
    val vendor_id: Int
)