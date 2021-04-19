package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dteti.bookapp.R
import com.dteti.bookapp.viewmodel.QuoteViewModel
import com.dteti.bookapp.databinding.ActivityMainBinding
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment

class MainActivity : AppCompatActivity() {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    private lateinit var b : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // assigning fragmentManager
        fragmentManager = supportFragmentManager
        // attaching fragments
        if (savedInstanceState == null){
            transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fr_trending_books, BookTopicFragment.newInstance("Trending Books"))
            transaction.commit()
            transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fr_best_selling, BookTopicFragment.newInstance("Best-Selling Books"))
            transaction.commit()
        }

        suspend {
            QuoteViewModel.quotesToView(b.quoteView, this)
            b.tvQuotes.text = QuoteViewModel.quotesGenerated
        }

        b.quoteView.setOnClickListener {
            QuoteViewModel.quotesToView(b.quoteView, this)
            b.tvQuotes.text = QuoteViewModel.quotesGenerated
        }
        b.ivFav.setOnClickListener {
            val intent = Intent(this, BookshelfActivity::class.java)
            startActivity(intent)
        }
        b.ivNotif.setOnClickListener { toastNotYet() }
        b.ivProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        QuoteViewModel.quotesToView(b.quoteView, this)
        b.tvQuotes.text = QuoteViewModel.quotesGenerated
        super.onResume()
    }

    private fun toastNotYet() {
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(baseContext, "Successfully exited the app", Toast.LENGTH_SHORT).show()
    }
}