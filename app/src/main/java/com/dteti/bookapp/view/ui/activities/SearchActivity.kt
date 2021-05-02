package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.view.adapter.BookAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.activity_search.view.*

class SearchActivity : AppCompatActivity() {

    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        tv_uhoh_description.text = "We couldn’t find any results for {{query}}. /nTry searching something else!"

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
        isBookFound(arrayListOf<Book>())
    }

    //check book availability
    private fun isBookFound(bookList: ArrayList<Book>) {
        if(bookList.isNullOrEmpty()) {
            ic_not_found.visibility = View.VISIBLE
            tv_uhoh.visibility = View.VISIBLE
            tv_uhoh_description.visibility = View.VISIBLE
            rv_search.visibility = View.GONE
        }
        else {
            bookAdapter.bookList = bookList
            ic_not_found.visibility = View.GONE
            tv_uhoh.visibility = View.GONE
            tv_uhoh_description.visibility = View.GONE
        }
    }
}