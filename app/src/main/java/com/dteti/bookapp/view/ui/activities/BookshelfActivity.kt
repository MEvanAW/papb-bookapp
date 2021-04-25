package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.databinding.ActivityBookshelfBinding
import com.dteti.bookapp.view.adapter.BookAdapter
import com.dteti.bookapp.view.adapter.BookshelfAdapter

class BookshelfActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookshelfBinding
    private lateinit var bookshelf : MutableList<Book>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookshelf)

        //get bookshelf list
        bookshelf = mutableListOf()

        //onClickListener
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

        //Assign recyclerview adapter
        binding.rvBookshelf.setHasFixedSize(true)
        val adapter = BookshelfAdapter(bookshelf, this)
        binding.rvBookshelf.adapter = adapter
        adapter.callableOnClick(object: BookshelfAdapter.OnItemClicked{
            override fun onItemClicked(book: Book){
                val intent = Intent(this@BookshelfActivity, BookDetailActivity::class.java)
                intent.putExtra("BOOK_DATA", book)
                startActivity(intent)
            }
        })
    }

    fun toastNotYet() {
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }
}