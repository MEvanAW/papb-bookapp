package com.dteti.bookapp

import android.app.Application
import com.dteti.bookapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BookApp : Application() {
    //override function to add startKoin
    override fun onCreate() {
        super.onCreate()

        val moduleList = listOf(
            vmBookDetail, vmQuote,
            vmBookShelf, vmSearch,
            vmProfile, quoteClient,
            bookClient, BooksRepo,
            appDatabase
        )
        //start Koin
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BookApp)
            modules(moduleList)
        }
    }
}