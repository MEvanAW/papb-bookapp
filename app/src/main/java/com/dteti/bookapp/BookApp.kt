package com.dteti.bookapp

import android.app.Application
import com.dteti.bookapp.di.vmBookDetail
import com.dteti.bookapp.di.vmBookShelf
import com.dteti.bookapp.di.vmQuote
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class BookApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@BookApp)
            modules(listOf(vmBookDetail, vmQuote, vmBookShelf))
        }
    }
}