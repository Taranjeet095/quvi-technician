package com.quviservicestechnician.data.network

import com.quviservicestechnician.data.response.*
import okhttp3.MultipartBody
import retrofit2.http.*
import java.io.File

/**
 * Created by Akash Singhal on 06/12/2021.
 */


interface AuthApi {

    @FormUrlEncoded
    @POST("technician/login")
    suspend fun login (
        @Field("phone") phone : String,
        @Field("country_code") country_code : String
    ) : UserInfoResponse

    @Headers("Content-Type: application/json")
    @GET("technician/service-list")
    suspend fun serviceList(
    ): ServiceListResponse

    @Multipart
    @POST("technician/update-technician-detail")
    suspend fun userDetailsUpdate(
        @Part("name") name : String,
        @Part("email") email : String,
        @Part("country") country : String,
        @Part("country_code") country_code: String,
        @Part("phone") phone: String,
//        @Part("image") image: File
    ): TechnicianUpdateDetailResponse


    @Headers("Content-Type: application/json")
    @GET("technician/new-booking-requests")
    suspend fun newBookingDetails(
    ): NewBookingResponse

    @Headers("Content-Type: application/json")
    @GET("technician/accept-order/{booking_id}")
    suspend fun acceptOrder(
        @Path("booking_id") booking_id: Int
    ): AcceptOrderResponse

    @Headers("Content-Type: application/json")
    @GET("technician/bookings")
    suspend fun bookings(
    ): BookingDetailsResponse
}