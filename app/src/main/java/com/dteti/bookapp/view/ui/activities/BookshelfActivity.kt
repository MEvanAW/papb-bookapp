package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
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
    private lateinit var adapter: BookshelfAdapter
    private var bookRoom = MutableLiveData<List<BookRoom>>()
    private var selectedFilter = 0  // 0: all, 1: reading now, 2: to read, 3: have read

    // custom tab
    // TODO: declare custom tab variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookshelf)

        binding.rvBookshelf.setHasFixedSize(true)

        // get all books
        bookRoom = bookshelfViewModel.getAllBooks()
        bookRoom.observe({ lifecycle }, { bookRooms ->
            run {
                val bookshelf = bookRoomListToBookMutableList(bookRooms)
                // assign adapter
                adapter = BookshelfAdapter(bookshelf, this)
                binding.rvBookshelf.adapter = adapter
                adapter.callableOnClick(object : BookshelfAdapter.OnItemClicked {
                    // when Continue Reading button in Bookshelf Clicked
                    override fun onItemClicked(book: Book) {
                        val intent = Intent(this@BookshelfActivity, BookDetailActivity::class.java)
                        intent.putExtra("BOOK_DATA", book)
                        startActivity(intent)
                    }
                    // when Delete button in Bookshelf Clicked
                    override fun onDeleteClicked(book: Book) {
                        lifecycleScope.launch(Dispatchers.IO) {
                            bookshelfViewModel.deleteBook(book)
                        }
                        adapter.bookshelf.remove(book)
                        adapter.filteredBooks.remove(book)
                        adapter.notifyDataSetChanged()
                    }
                })
            }
        })

        binding.searchBook.isIconifiedByDefault = false

        //onClickListener
        binding.searchBook.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotBlank()) {
                    val searchResult = adapter.bookshelf.filter { b: Book -> b.title.contains(query, true) }
                    if (!searchResult.isNullOrEmpty()) {
                        adapter.filteredBooks = searchResult.toMutableList()
                        adapter.notifyDataSetChanged()
                    }
                    else when(selectedFilter){
                        0 -> { toast("$query in All category not found.") }
                        1 -> { toast("$query in Reading Now category not found")}
                        2 -> { toast("$query in To Read category not found.") }
                    }
                }
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank())
                    when(selectedFilter){
                        0 -> showAllBooks()
                        1 -> showBooksByStatus(BookStatus.READING_NOW)
                        2 -> showBooksByStatus(BookStatus.TO_READ)
                    }
                return false
            }
        })
        binding.ivFilter.setOnClickListener{ v: View ->
            showMenu(v)
        }
        binding.ivNotif.setOnClickListener {
            toast("Not implemented yet.")
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

    // show filter popup menu
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
                toast("Not implemented yet.")
            }
        }
    }

    private fun showAllBooks(){
        adapter.filteredBooks = adapter.bookshelf
        adapter.notifyDataSetChanged()
    }

    private fun showBooksByStatus(bookStatus: BookStatus){
        adapter.filteredBooks = adapter.bookshelf.filter{ b: Book -> b.bookStatus == bookStatus}.toMutableList()
        adapter.notifyDataSetChanged()
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

    private fun toast(text: String){
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }
}