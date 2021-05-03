package com.dteti.bookapp.data

import androidx.room.*
import com.dteti.bookapp.data.model.BookRoom

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    suspend fun getAll(): List<BookRoom>

    @Insert
    suspend fun insertAll(vararg books: BookRoom)

    @Query("DELETE FROM book WHERE title = :title")
    suspend fun delete(title: String)

    @Update
    fun updateBook(vararg books: BookRoom)
}