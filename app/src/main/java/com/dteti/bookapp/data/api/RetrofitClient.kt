package com.dteti.bookapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val base_url = "https://goquotes-api.herokuapp.com/api/v1/"

    val instance : Quotes_Api by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitBuilder.create(Quotes_Api::class.java)
    }
}