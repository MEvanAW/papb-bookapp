package com.dteti.bookapp.viewmodel

import android.app.Application
import android.app.NotificationManager
import androidx.core.content.ContextCompat
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dteti.bookapp.data.AppDatabase
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BookRoom
import com.dteti.bookapp.data.model.BookStatus
import com.dteti.bookapp.view.utils.sendNotification
import kotlinx.coroutines.launch

class BookDetailViewModel(val app: Application): AndroidViewModel(app) {

    //initiate room database
    private val db = AppDatabase.getInstance(app)
    private val bookDao = db.bookDao()

    //notification manager
    var notificationManager = ContextCompat.getSystemService(app, NotificationManager::class.java) as NotificationManager

    fun insertBookAsReadingNow(book: Book){
        viewModelScope.launch{
            bookDao.insertAll(
                BookRoom(
                    title = book.title,
                    authors = book.authors,
                    description = book.description,
                    pageCount = book.pageCount,
                    categories = book.categories,
                    averageRating = book.averageRating,
                    smallThumbnail = book.imageLinks?.smallThumbnail,
                    thumbnail = book.imageLinks?.thumbnail,
                    small = book.imageLinks?.small,
                    medium = book.imageLinks?.medium,
                    large = book.imageLinks?.large,
                    extraLarge = book.imageLinks?.large,
                    previewLink = book.previewLink,
                    bookStatus = BookStatus.READING_NOW
                )
            )
        }
    }

    fun pushNotification() {
        notificationManager.sendNotification(app)
    }
}