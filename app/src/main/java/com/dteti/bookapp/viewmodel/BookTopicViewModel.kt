package com.dteti.bookapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.di.Dependencies

class BookTopicViewModel() : ViewModel() {
    private val booksRepository = Dependencies().booksRepo

    fun getBooksByTopic(topic: String): MutableLiveData<MutableList<Book>>{
        return booksRepository.getBooksByTopic(topic)
    }
}