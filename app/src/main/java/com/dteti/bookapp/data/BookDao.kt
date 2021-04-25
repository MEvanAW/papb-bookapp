package com.dteti.bookapp.data

import androidx.room.*
import com.dteti.bookapp.data.model.BookRoom

@Dao
interface BookDao {
    @Query("SELECT * FROM book")
    fun getAll(): List<BookRoom>

    @Insert
    suspend fun insertAll(vararg books: BookRoom)

    @Delete
    fun delete(book: BookRoom)

    @Update
    fun updateBook(vararg books: BookRoom)
}