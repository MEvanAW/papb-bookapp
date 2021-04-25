package com.dteti.bookapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dteti.bookapp.data.AppDatabase
import com.dteti.bookapp.data.model.BookRoom
import kotlinx.coroutines.launch

class BookshelfViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val bookDao = db.bookDao()
    fun getAllBook(): MutableLiveData<List<BookRoom>>{
        val bookListLiveData = MutableLiveData<List<BookRoom>>()
        viewModelScope.launch{
            bookListLiveData.value = bookDao.getAll()
        }
        return bookListLiveData
    }
}