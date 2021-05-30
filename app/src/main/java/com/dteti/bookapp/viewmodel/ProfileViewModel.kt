package com.dteti.bookapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dteti.bookapp.data.AppDatabase
import com.dteti.bookapp.data.model.BookRoom
import com.dteti.bookapp.data.model.BookStatus
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getInstance(application)
    private val bookDao = db.bookDao()
    private var bookListLiveData = MutableLiveData<List<BookRoom>>()

    fun getBooksByStatus(bookStatus: BookStatus): MutableLiveData<List<BookRoom>>{
        viewModelScope.launch{
            bookListLiveData.value = bookDao.getByStatus(bookStatus.ordinal)
        }
        return bookListLiveData
    }
}