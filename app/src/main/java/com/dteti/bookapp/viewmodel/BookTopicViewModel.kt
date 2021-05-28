package com.dteti.bookapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BookRoom
import com.dteti.bookapp.data.model.BookStatus
import com.dteti.bookapp.di.Dependencies
import kotlinx.coroutines.launch

class BookTopicViewModel() : ViewModel() {
    private val booksRepository = Dependencies().booksRepo

    //initiate room database
    private val db = Dependencies().appDatabase
    private val bookDao = db.bookDao()

    fun getBooksByTopic(topic: String): MutableLiveData<MutableList<Book>>{
        return booksRepository.getBooksByTopic(topic)
    }

    fun insertBookAsToRead(book: Book){
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
                    bookStatus = BookStatus.TO_READ
                )
            )
        }
    }
}