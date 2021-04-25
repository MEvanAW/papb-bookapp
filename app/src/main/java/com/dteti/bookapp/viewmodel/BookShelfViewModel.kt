package com.dteti.bookapp.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dteti.bookapp.data.AppDatabase
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.ImageLinks
import kotlinx.coroutines.launch

class BookShelfViewModel(context: Context) : ViewModel() {
    private val db = AppDatabase.getInstance(context)
    private val bookDao = db.bookDao()
    fun getAllBook(): MutableLiveData<MutableList<Book>>{
        val bookListLiveData = MutableLiveData<MutableList<Book>>()
        val bookList: MutableList<Book> = mutableListOf()
        viewModelScope.launch{
            suspend{
                val bookRoomList = bookDao.getAll()
                bookRoomList.forEach { bookRoom -> bookList.add(Book(
                    bookRoom.title,
                    bookRoom.authors,
                    bookRoom.description,
                    bookRoom.pageCount,
                    bookRoom.categories,
                    bookRoom.averageRating,
                    ImageLinks(
                        bookRoom.smallThumbnail,
                        bookRoom.thumbnail,
                        bookRoom.small,
                        bookRoom.medium,
                        bookRoom.large,
                        bookRoom.extraLarge
                    ),
                    bookRoom.previewLink,
                    bookRoom.bookStatus
                ))}
                bookListLiveData.value = bookList
            }
        }
        return bookListLiveData
    }
}