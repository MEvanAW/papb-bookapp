package com.dteti.bookapp.data.api

import com.dteti.bookapp.data.model.BooksApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    @GET("volumes?langRestrict=en&filter=full&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/averageRating,volumeInfo/pageCount,volumeInfo/description,volumeInfo/categories,volumeInfo/imageLinks/*,volumeInfo/previewLink)&key=AIzaSyA3IlIT2dqXGTUZ8G0gr-VT1-vUD4Q2dPA")
    fun getBooksByTopic(@Query("q") subject: String) : Call<BooksApiResponse>
}