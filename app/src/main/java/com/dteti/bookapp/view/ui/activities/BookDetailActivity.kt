package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dteti.bookapp.data.model.QuotesJSON
import com.dteti.bookapp.data.api.RetrofitClient
import com.dteti.bookapp.R
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class BookDetailActivity : AppCompatActivity(), View.OnClickListener {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    private var quotesJSON : String? = null
    private val backgroundArray = arrayListOf(R.drawable.ic_group_171, R.drawable.ic_group_172, R.drawable.ic_group_173, R.drawable.ic_group_174)
    private lateinit var background : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

        // assigning fragmentManager
        fragmentManager = supportFragmentManager
        // attaching fragments
        if (savedInstanceState == null){
            transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fr_similar_books, BookTopicFragment.newInstance("Similar Books"))
            transaction.commit()
        }

        background = findViewById<ConstraintLayout>(R.id.quoteView)
        quoteToView()

        background.setOnClickListener { quoteToView() }

        val ivBack: android.widget.ImageView = findViewById(R.id.iv_back)
        ivBack.setOnClickListener(this)

        val tvStartReading: android.widget.TextView = findViewById(R.id.tv_start_reading)
        tvStartReading.setOnClickListener(this)

        val iv_back: android.widget.ImageView = findViewById(R.id.iv_back)
        iv_back.setOnClickListener(this)

        val tv_start_reading: android.widget.TextView = findViewById(R.id.tv_start_reading)
        tv_start_reading.setOnClickListener(this)
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
        quoteToView()
        super.onResume()
    }

    fun quoteToView() {
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
                        Toast.makeText(this@BookDetailActivity, "Quotes unavailable while you're offline", Toast.LENGTH_SHORT).show()
                        t.printStackTrace()
                    }
                }
        )
    }
}