package com.dteti.bookapp.viewmodel

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dteti.bookapp.data.AppDatabase
import com.dteti.bookapp.data.BookDao
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BookRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



class BookshelfViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val bookDao = db.bookDao()
    private var bookListLiveData = MutableLiveData<List<BookRoom>>()

    fun getAllBook(): MutableLiveData<List<BookRoom>>{
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