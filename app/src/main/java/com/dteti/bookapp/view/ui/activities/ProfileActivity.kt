package com.dteti.bookapp.view.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BookRoom
import com.dteti.bookapp.data.model.ImageLinks
import com.dteti.bookapp.view.adapter.BookAdapter
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment
import com.dteti.bookapp.viewmodel.ProfileViewModel
import com.dteti.bookapp.viewmodel.ProfileViewModelFactory

class ProfileActivity : AppCompatActivity() {
    // view model
    private lateinit var profileViewModel: ProfileViewModel

    // attribute
    private var bookRoom = MutableLiveData<List<BookRoom>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // assign view model
        val profileViewModelFactory = ProfileViewModelFactory(application)
        profileViewModel = ViewModelProvider(this, profileViewModelFactory).get(ProfileViewModel::class.java)

        // assign recyclew view adapter
        val rvProfileBooks = findViewById<RecyclerView>(R.id.rv_profile_books)
        val adapter = BookAdapter(mutableListOf(), this, true)
        rvProfileBooks.adapter = adapter

        // get profile books
        bookRoom = profileViewModel.getAllBook()
        bookRoom.observe({ lifecycle }, { bookRooms ->
            run {
                val bookList: MutableList<Book> = mutableListOf()
                bookRooms.forEach{ bookRoom -> bookList.add(Book(
                    bookRoom.title,
                    bookRoom.authors,
                    bookRoom.description,
                    bookRoom.pageCount,
                    bookRoom.categories,
                    bookRoom.averageRating,
                    ImageLinks(
                        bookRoom.smallThumbnail,
                        bookRoom.thumbnail,
                        bookRoom.small,
                        bookRoom.medium,
                        bookRoom.large,
                        bookRoom.extraLarge
                    ),
                    bookRoom.previewLink,
                    bookRoom.bookStatus
                ))}
                adapter.bookList = bookList
                rvProfileBooks.adapter!!.notifyDataSetChanged()
            }
        })

        // on click listeners
        adapter.callableOnClick(object: BookAdapter.IOnBookClicked{
            override fun onBookClicked(book: Book){
                val intent = Intent(applicationContext, BookDetailActivity::class.java)
                intent.putExtra("BOOK_DATA", book)
                startActivity(intent)
            }
            override fun onReadLater(book: Book) { }
        })
        val favNav = findViewById<ImageView>(R.id.ivFav)
        val notifNav = findViewById<ImageView>(R.id.ivNotif)
        val homeNav = findViewById<ImageView>(R.id.ivHome)
        val tvHaveRead = findViewById<TextView>(R.id.tv_have_read)
        val tvReadingNow = findViewById<TextView>(R.id.tv_reading_now)
        val tvToRead = findViewById<TextView>(R.id.tv_to_read)

        tvHaveRead.setOnClickListener{
            //tvHaveRead.setTextColor(-903330)
            //tvReadingNow.setTextColor(-3881788)
            //tvToRead.setTextColor(-3881788)
            toastNotYet()
        }
        tvReadingNow.setOnClickListener{
            tvHaveRead.setTextColor(-3881788)
            tvReadingNow.setTextColor(-903330)
            tvToRead.setTextColor(-3881788)
        }
        tvToRead.setOnClickListener{
            tvHaveRead.setTextColor(-3881788)
            tvReadingNow.setTextColor(-3881788)
            tvToRead.setTextColor(-903330)
        }
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