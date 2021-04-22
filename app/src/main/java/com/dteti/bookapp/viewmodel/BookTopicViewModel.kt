package com.dteti.bookapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dteti.bookapp.data.api.BooksRepository
import com.dteti.bookapp.data.model.Book

class BookTopicViewModel() : ViewModel() {
    private val booksRepository = BooksRepository()

    fun getBooksByTopic(topic: String): MutableLiveData<MutableList<Book>>{
        return booksRepository.getBooksByTopic("\"" + topic.replace(" ", "+") + "\"")
    }
}