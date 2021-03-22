package com.dteti.bookapp.QuotesApi

import retrofit2.Call
import retrofit2.http.GET

interface Quotes_Api {
    @GET("random?count=1")
    fun getApi() : Call<QuotesJSON>
}