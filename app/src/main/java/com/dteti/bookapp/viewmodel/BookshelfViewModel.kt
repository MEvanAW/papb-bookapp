package com.dteti.bookapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BookRoom
import com.dteti.bookapp.di.Dependencies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class BookshelfViewModel(application: Application) : AndroidViewModel(application) {
    private val db = Dependencies().appDatabase
    private val bookDao = db.bookDao()
    private var bookListLiveData = MutableLiveData<List<BookRoom>>()

    fun getAllBooks(): MutableLiveData<List<BookRoom>>{
        viewModelScope.launch{
            bookListLiveData.value = bookDao.getAll()
        }
        return bookListLiveData
    }

    suspend fun deleteBook(book : Book){
        viewModelScope.launch(Dispatchers.IO) {
            bookDao.delete(book.title)
        }
    }

}