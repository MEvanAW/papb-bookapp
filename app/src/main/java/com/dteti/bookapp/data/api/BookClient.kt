package com.dteti.bookapp.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BookClient {
    private const val base_url = "https://www.googleapis.com/books/v1/"

    val instance : BooksApi by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitBuilder.create(BooksApi::class.java)
    }
}