package com.dteti.bookapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dteti.bookapp.data.api.BooksRepository
import com.dteti.bookapp.data.model.Book

class SearchViewModel() : ViewModel() {
    private val booksRepository = BooksRepository()

    fun getBooks(query: String):MutableLiveData<MutableList<Book>>{
        return booksRepository.getBooks(query)
    }
}