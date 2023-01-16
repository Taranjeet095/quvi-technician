package com.quviservicestechnician.data.network

import okhttp3.ResponseBody


/**
 * Created by Mormukut Singh on 09/08/2021.
 */

sealed class Resource<out T> {
    data class Success<out T>(val value: T) : Resource<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?
    ) : Resource<Nothing>()

    object Loading : Resource<Nothing>()
}