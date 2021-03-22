package com.dteti.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dteti.bookapp.QuotesApi.QuotesJSON
import com.dteti.bookapp.QuotesApi.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    private var quotesJSON : String? = null
    private val backgroundArray = arrayListOf(R.drawable.ic_group_171, R.drawable.ic_group_172, R.drawable.ic_group_173, R.drawable.ic_group_174)
    private lateinit var background : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

        background = findViewById<ConstraintLayout>(R.id.quoteView)
        suspend {
            quotesToView()
        }

        background.setOnClickListener { quotesToView() }

        val favNav = findViewById<ImageView>(R.id.ivFav)
        val notifNav = findViewById<ImageView>(R.id.ivNotif)
        val profileNav = findViewById<ImageView>(R.id.ivProfile)

        favNav.setOnClickListener { toastNotYet() }
        notifNav.setOnClickListener { toastNotYet() }
        profileNav.setOnClickListener { toastNotYet() }
    }

    fun quotesToView() {
        getQuotes()
        background = findViewById<ConstraintLayout>(R.id.quoteView)
        background.setBackgroundResource(backgroundArray[(0..3).random()])
        try {
            val textQuote = findViewById<TextView>(R.id.tvQuotes)
            if (quotesJSON!!.length < 250) {
                textQuote.text = quotesJSON
            }
        } catch (e :Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        quotesToView()
        super.onResume()
    }

    fun getQuotes() {
        RetrofitClient.instance.getApi().enqueue(
            object : Callback<QuotesJSON> {
                override fun onResponse(
                    call: Call<QuotesJSON>,
                    response: Response<QuotesJSON>
                ) {
                    response.body().let { quotesJSON = it!!.quotes[0].text }
                }

                override fun onFailure(call: Call<QuotesJSON>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "Quotes unavailable while you're offline", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            }
        )
    }

    fun toastNotYet() {
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toast.makeText(baseContext, "Successfully exited the app", Toast.LENGTH_SHORT).show()
    }
}