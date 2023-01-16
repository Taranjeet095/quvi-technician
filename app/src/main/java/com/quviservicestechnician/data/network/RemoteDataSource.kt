package com.quviservicestechnician.data.network

import androidx.databinding.ktx.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Akash Singhal on 09/08/2021.
 */

class RemoteDataSource {
    companion object {
//                        private const val BASE_URL = "http://10.130.34.44/api/"
            private const val BASE_URL = "http://13.232.39.100/api/v1/"
    }

    fun <Api> buildApi(
        api: Class<Api>,
        authToken: String? = null,
    ): Api {

//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(UnsafeOkHttpClient.getUnsafeOkHttpClient(authToken)
//            )
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//            .create(api)



        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(chain.request().newBuilder().also {
                        it.addHeader("Authorization", "Bearer $authToken")
                    }.build())

                }.also { client ->
                    if (BuildConfig.DEBUG) {
                        val logging = HttpLoggingInterceptor();
                        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                        client.addInterceptor(logging)
                    }
                }.build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(api)
    }

}