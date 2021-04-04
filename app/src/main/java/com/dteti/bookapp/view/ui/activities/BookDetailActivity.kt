package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dteti.bookapp.R
import com.dteti.bookapp.databinding.ActivityBookDetailBinding
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment

class BookDetailActivity : AppCompatActivity(), View.OnClickListener {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    private lateinit var b : ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        // assigning fragmentManager
        fragmentManager = supportFragmentManager
        // attaching fragments
        if (savedInstanceState == null){
            transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fr_similar_books, BookTopicFragment.newInstance("Similar Books"))
            transaction.commit()
        }

        QuoteViewModel.quotesToView(b.quoteView, this)
        b.tvQuotes.text = QuoteViewModel.quotesGenerated

        b.quoteView.setOnClickListener {
            QuoteViewModel.quotesToView(b.quoteView, this)
            b.tvQuotes.text = QuoteViewModel.quotesGenerated
        }
        b.ivBack.setOnClickListener(this)
        b.tvStartReading.setOnClickListener(this)
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

    override fun onResume() {
        QuoteViewModel.quotesToView(b.quoteView, this)
        b.tvQuotes.text = QuoteViewModel.quotesGenerated
        super.onResume()
    }

}