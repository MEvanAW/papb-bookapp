package com.dteti.bookapp.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dteti.bookapp.R

class ReadingActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading)

        // Digunakan untuk kembali ke activity sebelumnya
        val iv_back: android.widget.ImageView = findViewById(R.id.iv_back)
        iv_back.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> finish()
        }
    }
}