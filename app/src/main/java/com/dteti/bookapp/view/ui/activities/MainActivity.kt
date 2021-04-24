package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.dteti.bookapp.R
import com.dteti.bookapp.viewmodel.QuoteViewModel
import com.dteti.bookapp.databinding.ActivityMainBinding
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment

class MainActivity : AppCompatActivity() {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    //data Binding and View Model
    private lateinit var b : ActivityMainBinding
    private lateinit var quoteViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // assigning fragmentManager
        fragmentManager = supportFragmentManager
        // attaching fragments
        if (savedInstanceState == null){
            transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fr_rv_1, BookTopicFragment.newInstance("Biography", "Biography", null))
            transaction.add(R.id.fr_rv_2, BookTopicFragment.newInstance("\"Self+Help\"", "Self Help", null))
            transaction.add(R.id.fr_rv_3, BookTopicFragment.newInstance("Health OR Fitness OR Dieting", "Health, Fitness, and Dieting", null))
            transaction.add(R.id.fr_rv_4, BookTopicFragment.newInstance("Business OR Money","Business and Money", null))
            transaction.commit()
        }

        //Get View Model
        quoteViewModel = ViewModelProviders.of(this).get(QuoteViewModel::class.java)

        quoteViewModel.getQuotes(this)

        b.quoteView.setOnClickListener {
            quoteViewModel.getQuotes(this)
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

        quoteViewModel.quotesGenerated.observe(this, Observer {
            b.tvQuotes.text = quoteViewModel.quotesGenerated.value
            b.quoteView.setBackgroundResource(quoteViewModel.background.value!!)
        })
    }

    override fun onResume() {
        quoteViewModel.getQuotes(this)
        super.onResume()
    }

    private fun toastNotYet() {
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }
}