package com.dteti.bookapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book (
    val title : String? = null,
    val authors : List<String>? = null,
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val averageRating : Double? = null,
    val imageLinks: ImageLinks? = null,
    val previewLink: String? = null
) : Parcelable