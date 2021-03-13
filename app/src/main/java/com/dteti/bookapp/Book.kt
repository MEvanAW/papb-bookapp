package com.dteti.bookapp

import android.graphics.Bitmap
import android.media.Image

data class Book (
    val title : String,
    val author : String,
    val rating : String,
    val bookCover : Bitmap
)