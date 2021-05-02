package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.view.adapter.BookAdapter
import com.dteti.bookapp.viewmodel.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*

class SearchActivity : AppCompatActivity() {
    // view model
    private lateinit var searchViewModel: SearchViewModel

    // adapter
    private lateinit var bookAdapter: BookAdapter

    // query
    private var query: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        // get view model
        searchViewModel = SearchViewModel()

        // accepting EXTRA
        query = intent.getStringExtra("QUERY")

        // set tv uhoh description text
        searchBook.setQuery(query, false)
        searchBook.clearFocus()
        tv_uhoh_description.text = "We couldn’t find any results for $query.\nTry searching something else!"

        // set and assign recyclerView adapter
        bookAdapter = BookAdapter(mutableListOf(), this)
        bookAdapter.callableOnClick(object: BookAdapter.IOnBookClicked{
            override fun onBookClicked(book: Book){
                val intent = Intent(this@SearchActivity, BookDetailActivity::class.java)
                intent.putExtra("BOOK_DATA", book)
                startActivity(intent)
            }
        })
        rv_search.adapter = bookAdapter
        rv_search.layoutManager = GridLayoutManager(this, 2)

        // get books from Google Books API
        searchViewModel.getBooks(query!!).observe({ lifecycle }, { bookList ->
            run{
                isBookFound(bookList)
            }
        })

        // set listeners
        searchBook.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchViewModel.getBooks(query!!).observe({ lifecycle }, { bookList ->
                    run{
                        isBookFound(bookList)
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
        //searchBook.setQuery(query, false)
        //searchBook.clearFocus()
    }

    //check book availability
    private fun isBookFound(bookList: MutableList<Book>) {
        if(bookList.isNullOrEmpty()) {
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