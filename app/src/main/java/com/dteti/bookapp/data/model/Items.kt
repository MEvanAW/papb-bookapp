package com.dteti.bookapp.data.model

import com.google.gson.annotations.SerializedName

data class Items(
    @SerializedName ("volumeInfo")
    val book: Book
)
