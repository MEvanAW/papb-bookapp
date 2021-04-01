package com.dteti.bookapp.api

import retrofit2.Call
import retrofit2.http.GET

interface Quotes_Api {
    @GET("random?count=1")
    fun getApi() : Call<QuotesJSON>
}