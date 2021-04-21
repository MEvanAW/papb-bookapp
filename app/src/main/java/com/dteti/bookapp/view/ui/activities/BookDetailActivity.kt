package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.viewmodel.QuoteViewModel
import com.dteti.bookapp.databinding.ActivityBookDetailBinding
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment

class BookDetailActivity : AppCompatActivity(), View.OnClickListener {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    private lateinit var binding : ActivityBookDetailBinding

    // book data
    private var book: Book? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        // assigning fragmentManager
        fragmentManager = supportFragmentManager
        // attaching fragments
        if (savedInstanceState == null){
            transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fr_similar_books, BookTopicFragment.newInstance("Similar Books"))
            transaction.commit()
        }

        // accepting EXTRA
        book = intent.getParcelableExtra("BOOK_DATA")

        // assigns book data into views
        fillViewsWithData()

        // viewing quotes
        QuoteViewModel.quotesToView(binding.quoteView, this)
        binding.tvQuotes.text = QuoteViewModel.quotesGenerated

        // setting OnClickListeners
        binding.quoteView.setOnClickListener {
            QuoteViewModel.quotesToView(binding.quoteView, this)
            binding.tvQuotes.text = QuoteViewModel.quotesGenerated
        }
        binding.ivBack.setOnClickListener(this)
        binding.tvStartReading.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> finish()
            R.id.tv_start_reading -> {
                val intent = Intent(this, ReadingActivity::class.java)
                startActivity(intent)
            }
        }
    }

    // Refreshes quote when activity resumes
    override fun onResume() {
        QuoteViewModel.quotesToView(binding.quoteView, this)
        binding.tvQuotes.text = QuoteViewModel.quotesGenerated
        super.onResume()
    }

    private fun fillViewsWithData(){
        // do nothing if book is null
        if(book == null) return
        // fill imageView
        when {
            book!!.imageLinks == null -> binding.ivBookCover.setImageResource(R.drawable.ic_book_cover_not_available)
            book!!.imageLinks!!.small != null -> glideLoad(book!!.imageLinks!!.small!!)
            book!!.imageLinks!!.large != null -> glideLoad(book!!.imageLinks!!.large!!)
            book!!.imageLinks!!.extraLarge != null -> glideLoad(book!!.imageLinks!!.extraLarge!!)
            book!!.imageLinks!!.thumbnail != null -> glideLoad(book!!.imageLinks!!.thumbnail!!)
            book!!.imageLinks!!.smallThumbnail != null -> glideLoad(book!!.imageLinks!!.smallThumbnail!!)
            else -> binding.ivBookCover.setImageResource(R.drawable.ic_book_cover_not_available)
        }
        // fill title
        if (book!!.title != null)
            binding.tvBookTitle.text = book!!.title
        else
            binding.tvBookTitle.text = "No Title"
        // fill authors
        if (book!!.authors != null){
            var authors = ""
            for(author in book!!.authors!!)
                authors += "$author, "
            binding.tvBookAuthor.text = authors.dropLast(2)
        }
        else
            binding.tvBookAuthor.text = "Anonym"
        // fill rating
        if (book!!.averageRating != null)
            binding.tvRating.text = book!!.averageRating.toString()
        // fill category
        if (book!!.categories != null){
            var categories = ""
            for (category in book!!.categories!!)
                categories += "$category,\n"
            binding.tvCategory.text = categories.dropLast(2)
        }
        // fill page count
        if (book!!.pageCount != null)
            binding.tvPageCount.text = book!!.pageCount.toString()
        // fill description
        if (book!!.description != null)
            binding.tvDescriptionText.text = book!!.description + "\n"
    }

    private fun glideLoad(url: String){
        try { Glide.with(this).load(url).into(binding.ivBookCover) }
        catch (e: Exception) { e.printStackTrace() }
    }
}