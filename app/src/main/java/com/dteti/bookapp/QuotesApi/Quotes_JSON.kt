package com.dteti.bookapp.QuotesApi

data class QuotesJSON (
    val status : Int,
    val message : String,
    val count : Int,
    val quotes : List<Quotes>
)