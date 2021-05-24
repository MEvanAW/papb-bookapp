package com.dteti.bookapp.di

import com.dteti.bookapp.data.AppDatabase
import com.dteti.bookapp.viewmodel.BookDetailViewModel
import com.dteti.bookapp.viewmodel.BookshelfViewModel
import com.dteti.bookapp.viewmodel.QuoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vmBookDetail = module {
    viewModel {
        BookDetailViewModel(get())
    }
}

val vmQuote = module {
    viewModel {
        QuoteViewModel()
    }
}

val vmBookShelf = module {
    viewModel {
        BookshelfViewModel(get())
    }
}