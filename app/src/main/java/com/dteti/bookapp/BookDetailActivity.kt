package com.dteti.bookapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresPermission

class BookDetailActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)

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
}