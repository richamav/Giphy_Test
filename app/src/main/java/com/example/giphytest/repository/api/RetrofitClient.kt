package com.example.giphytest.repository.api

import com.example.giphytest.utils.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient() {

    private val interceptor by lazy { HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY } }
    private val httpClient by lazy { OkHttpClient.Builder().addInterceptor(interceptor).build() }

    private val retrofitInstance by lazy {
        Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getRetrofitApiInstance(): RetrofitApiInterface =
        retrofitInstance.create(RetrofitApiInterface::class.java)

}