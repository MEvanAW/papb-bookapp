package com.dteti.bookapp.view.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.dteti.bookapp.R
import com.dteti.bookapp.databinding.ActivityReadingBinding

class ReadingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading)

        // Data binding
        val binding: ActivityReadingBinding = DataBindingUtil.setContentView(this, R.layout.activity_reading)
        // Digunakan untuk kembali ke activity sebelumnya
        binding.ivBack.setOnClickListener{ super.onBackPressed() }
    }
}