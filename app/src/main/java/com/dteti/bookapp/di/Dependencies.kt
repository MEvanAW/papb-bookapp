package com.dteti.bookapp.di

import android.content.Context
import androidx.room.Room
import com.dteti.bookapp.data.AppDatabase
import com.dteti.bookapp.data.api.*
import com.dteti.bookapp.viewmodel.*
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

val vmSearch = module {
    viewModel {
        SearchViewModel()
    }
}

val vmProfile = module {
    viewModel {
        ProfileViewModel(get())
    }
}

val quoteClient = module {
    val base_url = "https://goquotes-api.herokuapp.com/api/v1/"

    val instance : Quotes_Api by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitBuilder.create(Quotes_Api::class.java)
    }

    single {
        instance
    }
}

val bookClient = module {

    val base_url = "https://www.googleapis.com/books/v1/"
    val instance : BooksApi by lazy {
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitBuilder.create(BooksApi::class.java)
    }

    single {
        instance
    }
}

val appDatabase = module {
    var INSTANCE: AppDatabase? = null
    fun getInstance(context: Context): AppDatabase{
        synchronized(this){
            var instance = INSTANCE
            if (instance == null){
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
            }
            return instance
        }
    }

    single { getInstance(get()) }
}

val BooksRepo = module {
    single { BooksRepository() }
}


//Dependency Container for non activity or Fragment Inject
class Dependencies : KoinComponent {

    val quoteCl : Quotes_Api by inject()
    val booksRepo : BooksRepository by inject()
    val bookCl : BooksApi by inject()
    val appDatabase : AppDatabase by inject()

}
