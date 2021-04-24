package com.dteti.bookapp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BookRoom(
    @PrimaryKey val id: Int,
    val title : String = "No Title",
    val authors : List<String> = listOf("Anonym"),
    val description: String? = null,
    @ColumnInfo(name = "page_count") val pageCount: Int? = null,
    val categories: List<String>? = null,
    @ColumnInfo(name = "average_rating") val averageRating : Double? = null,
    @ColumnInfo(name = "small_thumbnail") val smallThumbnail: String? = null,
    val thumbnail: String? = null,
    val small: String? = null,
    val medium: String? = null,
    val large: String? = null,
    @ColumnInfo(name = "extra_large") val extraLarge: String? = null,
    @ColumnInfo(name = "preview_link") val previewLink: String? = null
)
