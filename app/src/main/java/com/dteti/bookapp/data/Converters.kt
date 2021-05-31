package com.dteti.bookapp.data

import androidx.room.TypeConverter
import com.dteti.bookapp.data.model.BookStatus
import com.google.gson.Gson

class Converters {
    @TypeConverter
    fun listToJson(value: List<String>?) = Gson().toJson(value)
    @TypeConverter
    fun jsonToList(value: String): List<String>{
        val fromJson =  Gson().fromJson(value, Array<String>::class.java)
        return fromJson?.toList() ?: listOf("Anonym")
    }
    @TypeConverter
    fun ordinalToBookStatus(value: Int) = enumValues<BookStatus>()[value]
    @TypeConverter
    fun bookStatusToOrdinal(value: BookStatus) = value.ordinal
}