package com.dteti.bookapp.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book (
    val title : String = "No Title",
    val authors : List<String> = listOf("Anonym"),
    val description: String? = null,
    val pageCount: Int? = null,
    val categories: List<String>? = null,
    val averageRating : Double? = null,
    val imageLinks: ImageLinks? = null,
    val previewLink: String? = null,
    val bookStatus: BookStatus? = null
) : Parcelable