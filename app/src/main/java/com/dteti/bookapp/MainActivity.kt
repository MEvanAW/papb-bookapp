package com.dteti.bookapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction

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

        val favNav = findViewById<ImageView>(R.id.ivFav)
        val notifNav = findViewById<ImageView>(R.id.ivNotif)
        val profileNav = findViewById<ImageView>(R.id.ivProfile)

        favNav.setOnClickListener { toastNotYet() }
        notifNav.setOnClickListener { toastNotYet() }
        profileNav.setOnClickListener { toastNotYet() }
    }

    fun toastNotYet() {
        Toast.makeText(this, "Not yet implemented", Toast.LENGTH_LONG).show()
    }
}