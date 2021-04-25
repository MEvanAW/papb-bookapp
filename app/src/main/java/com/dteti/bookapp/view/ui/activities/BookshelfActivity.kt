package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.databinding.ActivityBookshelfBinding
import com.dteti.bookapp.view.adapter.BookshelfAdapter
import com.dteti.bookapp.viewmodel.BookshelfViewModel
import com.dteti.bookapp.viewmodel.BookshelfViewModelFactory

class BookshelfActivity : AppCompatActivity() {
    // data binding
    private lateinit var binding: ActivityBookshelfBinding

    // view model
    private lateinit var bookshelfViewModel: BookshelfViewModel

    // custom tab
    // TODO: declare custom tab variables

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bookshelf)

        // assign view model
        val bookshelfViewModelFactory = BookshelfViewModelFactory(application)
        bookshelfViewModel = ViewModelProvider(this, bookshelfViewModelFactory).get(BookshelfViewModel::class.java)

        // assign recyclerview adapter
        binding.rvBookshelf.setHasFixedSize(true)
        val adapter = BookshelfAdapter(mutableListOf(), this)
        binding.rvBookshelf.adapter = adapter

        // get bookshelf
        bookshelfViewModel.getAllBook().observe({ lifecycle }, { bookshelf ->
            run {
                adapter.bookshelf = bookshelf
                binding.rvBookshelf.adapter!!.notifyDataSetChanged()
            }
        })

        //onClickListener
        adapter.callableOnClick(object: BookshelfAdapter.OnItemClicked{
            override fun onItemClicked(book: Book){
                val intent = Intent(this@BookshelfActivity, BookDetailActivity::class.java)
                intent.putExtra("BOOK_DATA", book)
                startActivity(intent)
            }
        })
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

    fun toastNotYet() {
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }
}