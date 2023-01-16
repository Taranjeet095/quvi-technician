package com.quviservicestechnician.data.response

data class ServiceListDataRes(
    val category: ServiceCategoryListRes,
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
    val subcategory: ServiceSubCategoryListRes,
    val subcategory_id: Int,
    val updated_at: String,
    val vendor_id: Int
)