package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.dteti.bookapp.R
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment

class ProfileActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        fragmentManager = supportFragmentManager
        // attaching fragments
        if (savedInstanceState == null){
            transaction = fragmentManager.beginTransaction()
            transaction.add(R.id.fr_reading_books, BookTopicFragment.newInstance("Biography", "", null))
            transaction.commit()
        }

        val favNav = findViewById<ImageView>(R.id.ivFav)
        val notifNav = findViewById<ImageView>(R.id.ivNotif)
        val homeNav = findViewById<ImageView>(R.id.ivHome)

        favNav.setOnClickListener {
            val intent = Intent(this, BookshelfActivity::class.java)
            startActivity(intent)
        }
        notifNav.setOnClickListener {
            toastNotYet()
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