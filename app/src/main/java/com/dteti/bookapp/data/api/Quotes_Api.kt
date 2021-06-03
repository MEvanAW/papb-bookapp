package com.dteti.bookapp.data.api

import com.dteti.bookapp.data.model.QuotesJSON
import retrofit2.Call
import retrofit2.http.GET

interface Quotes_Api {
    //query ke go quotes untuk mendapatkan 1 quotes
    @GET("random?count=1")
    fun getApi() : Call<QuotesJSON>
}