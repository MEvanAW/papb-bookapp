package com.dteti.bookapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BookshelfViewModelFactory(private val application: Application): ViewModelProvider.Factory {
    override fun <T: ViewModel?> create(modelClass: Class<T>):T {
        if (modelClass.isAssignableFrom(BookshelfViewModel::class.java)){
            return BookshelfViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}