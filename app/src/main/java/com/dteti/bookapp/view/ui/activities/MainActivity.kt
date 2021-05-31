package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
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
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    //data Binding and View Model
    private lateinit var b : ActivityMainBinding

    //get instance of View Model with Koin
    private val quoteViewModel: QuoteViewModel by viewModel()

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

        quoteViewModel.getQuotes(this)

        quoteViewModel.quotesGenerated.observe(this, Observer {
            b.tvQuotes.text = quoteViewModel.quotesGenerated.value
            b.quoteView.setBackgroundResource(quoteViewModel.background.value!!)
        })

        b.searchBook.isIconifiedByDefault = false

        // set listeners
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
        b.searchBook.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                val intent = Intent(applicationContext, SearchActivity::class.java)
                intent.putExtra("QUERY", query)
                startActivity(intent)
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO: consider text change processing
                return false
            }
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