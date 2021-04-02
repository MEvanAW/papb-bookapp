package com.dteti.bookapp.api

import com.dteti.bookapp.model.Quotes

data class QuotesJSON (
    val status : Int,
    val message : String,
    val count : Int,
    val quotes : List<Quotes>
)