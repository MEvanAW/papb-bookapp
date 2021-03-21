package com.dteti.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresPermission
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dteti.bookapp.QuotesApi.QuotesJSON
import com.dteti.bookapp.QuotesApi.RetrofitClient
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
        background.setBackgroundResource(backgroundArray[(0..3).random()])
        try {
            val textQuote = findViewById<TextView>(R.id.tvQuotes)
            if (quotesJSON!!.length > 100) {
                textQuote.text = quotesJSON
            } else {
                quoteToView()
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