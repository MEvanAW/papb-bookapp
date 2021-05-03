package com.dteti.bookapp.data.api

import com.dteti.bookapp.data.model.BooksApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BooksApi {
    // Query ke Google Books API untuk meminta daftar buku sesuai parameter q. Detail parameter q dapat dilihat pada dokumentasi resmi Google Books API.
    @GET("volumes?langRestrict=en&filter=full&orderBy=newest&fields=items(volumeInfo/title,volumeInfo/authors,volumeInfo/averageRating,volumeInfo/pageCount,volumeInfo/description,volumeInfo/categories,volumeInfo/imageLinks/*,volumeInfo/previewLink)&key=AIzaSyA3IlIT2dqXGTUZ8G0gr-VT1-vUD4Q2dPA")
    fun getBooks(@Query("q") subject: String) : Call<BooksApiResponse>
}