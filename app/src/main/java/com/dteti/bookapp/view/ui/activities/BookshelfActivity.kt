package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BookRoom
import com.dteti.bookapp.data.model.BookStatus
import com.dteti.bookapp.data.model.ImageLinks
import com.dteti.bookapp.databinding.ActivityBookshelfBinding
import com.dteti.bookapp.view.adapter.BookshelfAdapter
import com.dteti.bookapp.viewmodel.BookshelfViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.viewmodel.ext.android.viewModel

class BookshelfActivity : AppCompatActivity() {
    // data binding
    private lateinit var binding: ActivityBookshelfBinding

    // view model
    private val bookshelfViewModel: BookshelfViewModel by viewModel()

    // attribute
    private var bookRoom = MutableLiveData<List<BookRoom>>()
    private val adapter = BookshelfAdapter(mutableListOf(), this)
    private var selectedFilter = 0  // 0: all, 1: reading now, 2: to read, 3: have read

    // custom tab
    // TODO: declare custom tab variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookshelf)

        // assign recyclerview adapter
        binding.rvBookshelf.setHasFixedSize(true)
        binding.rvBookshelf.adapter = adapter

        showAllBooks()

        binding.searchBook.isIconifiedByDefault = false

        //onClickListener
        adapter.callableOnClick(object : BookshelfAdapter.OnItemClicked {
            //when Continue Reading button in BookShelf Clicked
            override fun onItemClicked(book: Book) {
                val intent = Intent(this@BookshelfActivity, BookDetailActivity::class.java)
                intent.putExtra("BOOK_DATA", book)
                startActivity(intent)
            }

            //when delete button in BookShelf Clicked
            override fun onDeleteClicked(book: Book) {
                lifecycleScope.launch(Dispatchers.IO) {
                    bookshelfViewModel.deleteBook(book)
                }
                adapter.bookshelf.remove(book)
                adapter.notifyDataSetChanged()
            }
        })
        binding.ivFilter.setOnClickListener{ v: View ->
            showMenu(v)
        }
        binding.ivNotif.setOnClickListener {
            toastNotYet()
        }
        binding.ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        binding.ivHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showMenu(v: View){
        val popup = PopupMenu(this, v)
        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
        popup.menu.getItem(selectedFilter).isChecked = true
        // Show the popup menu
        popup.show()
    }

    fun onFilterItemClick(item: MenuItem){
        when(item.itemId){
            R.id.option_all -> {
                if (selectedFilter != 0){
                    selectedFilter = 0
                    showAllBooks()
                }
            }
            R.id.option_reading_now -> {
                if (selectedFilter != 1){
                    selectedFilter = 1
                    showBooksByStatus(BookStatus.READING_NOW)
                }
            }
            R.id.option_to_read -> {
                if (selectedFilter != 2){
                    selectedFilter = 2
                    showBooksByStatus(BookStatus.TO_READ)
                }
            }
            R.id.option_have_read -> {
                toastNotYet()
            }
        }
    }

    private fun showAllBooks(){
        bookRoom = bookshelfViewModel.getAllBooks()
        bookRoom.observe({ lifecycle }, { bookRooms ->
            run {
                val bookshelf = bookRoomListToBookMutableList(bookRooms)
                adapter.bookshelf = bookshelf
                binding.rvBookshelf.adapter!!.notifyDataSetChanged()
            }
        })
    }

    private fun showBooksByStatus(bookStatus: BookStatus){
        bookRoom = bookshelfViewModel.getBooksByStatus(bookStatus)
        bookRoom.observe({ lifecycle }, { bookRooms ->
            run {
                val bookshelf = bookRoomListToBookMutableList(bookRooms)
                adapter.bookshelf = bookshelf
                binding.rvBookshelf.adapter!!.notifyDataSetChanged()
            }
        })
    }

    private fun bookRoomListToBookMutableList(bookRooms: List<BookRoom>): MutableList<Book>{
        val bookshelf: MutableList<Book> = mutableListOf()
        bookRooms.forEach{ bookRoom -> bookshelf.add(Book(
            bookRoom.title,
            bookRoom.authors,
            bookRoom.description,
            bookRoom.pageCount,
            bookRoom.categories,
            bookRoom.averageRating,
            ImageLinks(
                bookRoom.smallThumbnail,
                bookRoom.thumbnail,
                bookRoom.small,
                bookRoom.medium,
                bookRoom.large,
                bookRoom.extraLarge
            ),
            bookRoom.previewLink,
            bookRoom.bookStatus
        ))}
        return bookshelf
    }

    private fun toastNotYet(){
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }
}