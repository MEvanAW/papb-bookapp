package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.view.adapter.BookAdapter
import com.dteti.bookapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*
import org.koin.android.viewmodel.compat.ScopeCompat.viewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SearchActivity : AppCompatActivity() {
    // view model
    private val searchViewModel: SearchViewModel by viewModel()

    // adapter
    private lateinit var bookAdapter: BookAdapter

    // query
    private var query: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // accepting EXTRA
        query = intent.getStringExtra("QUERY")

        // set and assign recyclerView adapter
        bookAdapter = BookAdapter(mutableListOf(), this, false)
        bookAdapter.callableOnClick(object: BookAdapter.IOnBookClicked{
            override fun onBookClicked(book: Book){
                val intent = Intent(this@SearchActivity, BookDetailActivity::class.java)
                intent.putExtra("BOOK_DATA", book)
                startActivity(intent)
            }
            override fun onReadLater(book: Book) {
                searchViewModel.insertBookAsToRead(book)
                Toast.makeText(
                    applicationContext,
                    book.title + " is added to be read later.",
                    Toast.LENGTH_LONG).show()
            }
        })
        rv_search.adapter = bookAdapter
        rv_search.layoutManager = GridLayoutManager(this, 2)

        // get books from Google Books API
        searchViewModel.getBooks(query!!).observe({ lifecycle }, { bookList ->
            run{
                isBookFound(query, bookList)
            }
        })

        searchBook.isIconifiedByDefault = false

        // set listeners
        searchBook.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.getBooks(query!!).observe({ lifecycle }, { bookList ->
                    run{
                        isBookFound(query, bookList)
                    }
                })
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: consider text change processing
                return false
            }
        })

        // set searchView default text value
        searchBook.setQuery(query, false)
    }

    //check book availability
    private fun isBookFound(query: String?, bookList: MutableList<Book>) {
        if(bookList.isNullOrEmpty()) {
            tv_uhoh_description.text = "We couldn’t find any results for $query.\nTry searching something else!"
            ic_not_found.visibility = View.VISIBLE
            tv_uhoh.visibility = View.VISIBLE
            tv_uhoh_description.visibility = View.VISIBLE
            rv_search.visibility = View.GONE
        }
        else {
            bookAdapter.bookList = bookList
            bookAdapter.notifyDataSetChanged()
            rv_search.visibility = View.VISIBLE
            ic_not_found.visibility = View.GONE
            tv_uhoh.visibility = View.GONE
            tv_uhoh_description.visibility = View.GONE
        }
    }
}