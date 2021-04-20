package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.dteti.bookapp.R

class BookshelfActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bookshelf)

        val notifNav = findViewById<ImageView>(R.id.ivNotif)
        val profileNav = findViewById<ImageView>(R.id.ivProfile)
        val homeNav = findViewById<ImageView>(R.id.ivHome)

        notifNav.setOnClickListener {
            toastNotYet()
        }
        profileNav.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }
        homeNav.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun toastNotYet() {
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_SHORT).show()
    }
}