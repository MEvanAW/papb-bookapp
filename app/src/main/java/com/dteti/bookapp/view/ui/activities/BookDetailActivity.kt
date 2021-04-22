package com.dteti.bookapp.view.ui.activities

import android.content.ComponentName
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsClient
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsServiceConnection
import androidx.browser.customtabs.CustomTabsSession
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.viewmodel.QuoteViewModel
import com.dteti.bookapp.databinding.ActivityBookDetailBinding
import com.dteti.bookapp.view.ui.fragments.BookTopicFragment

class BookDetailActivity : AppCompatActivity(), View.OnClickListener {
    // fragmentManager initiation
    private lateinit var fragmentManager: FragmentManager
    private lateinit var transaction: FragmentTransaction
    private lateinit var binding : ActivityBookDetailBinding

    // book data
    private var book: Book? = null

    // custom tab
    private var mCustomTabsServiceConnection: CustomTabsServiceConnection? = null
    private var mClient: CustomTabsClient? = null
    private var mCustomTabsSession: CustomTabsSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_book_detail)

        // accepting EXTRA
        book = intent.getParcelableExtra("BOOK_DATA")

        // assigns book data into views
        fillViewsWithData()

        // viewing quotes
        QuoteViewModel.quotesToView(binding.quoteView, this)
        binding.tvQuotes.text = QuoteViewModel.quotesGenerated

        // assigning fragmentManager
        fragmentManager = supportFragmentManager

        // attaching fragments
        if (savedInstanceState == null){
            var topic = ""
            transaction = fragmentManager.beginTransaction()
            if (book != null){
                if (!book!!.categories.isNullOrEmpty())
                    topic = "\"" + book!!.categories!![0].replace(" ", "+") + "\""
                else if (!book!!.title.isNullOrBlank())
                    topic = book!!.title!!.replace(" ", "+")
            }
            transaction.add(R.id.fr_similar_books, BookTopicFragment.newInstance(topic,"Similar Books", book))
            transaction.commit()
        }

        // warm up the browser
        mCustomTabsServiceConnection = object: CustomTabsServiceConnection(){
            override fun onCustomTabsServiceConnected(name: ComponentName, client: CustomTabsClient) {
                // pre-warming
                mClient = client
                mClient?.warmup(0L)
                mCustomTabsSession = mClient?.newSession(null)
                mCustomTabsSession!!.mayLaunchUrl(book!!.previewLink!!.toUri(),null,null)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                mClient = null
            }
        }
        if(mCustomTabsSession != null)
            CustomTabsClient.bindCustomTabsService(this, "com.android.chrome", mCustomTabsServiceConnection!!)

        // setting OnClickListeners
        binding.quoteView.setOnClickListener {
            QuoteViewModel.quotesToView(binding.quoteView, this)
            binding.tvQuotes.text = QuoteViewModel.quotesGenerated
        }
        binding.ivBack.setOnClickListener(this)
        binding.tvStartReading.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.iv_back -> finish()
            R.id.tv_start_reading -> {
                /*val intent = Intent(this, ReadingActivity::class.java)
                startActivity(intent)*/
                if (book != null)
                    if (!book!!.previewLink.isNullOrBlank()){
                        var builder = CustomTabsIntent.Builder()
                        if (mCustomTabsSession != null)
                            builder = CustomTabsIntent.Builder(mCustomTabsSession)
                        // builder.setActionButton(icon, description, pendingIntent, tint)
                        val customTabsIntent = builder.build()
                        customTabsIntent.launchUrl(this, book!!.previewLink!!.toUri())
                        Toast.makeText(
                            this,
                            "It is recommended to have Google Chrome as default browser " +
                                "and use landscape orientation.",
                            Toast.LENGTH_LONG).show()
                    }
            }
        }
    }

    // Refreshes quote when activity resumes
    override fun onResume() {
        QuoteViewModel.quotesToView(binding.quoteView, this)
        binding.tvQuotes.text = QuoteViewModel.quotesGenerated
        super.onResume()
    }

    private fun fillViewsWithData(){
        // do nothing if book is null
        if(book == null) return
        // fill imageView
        when {
            book!!.imageLinks == null -> binding.ivBookCover.setImageResource(R.drawable.ic_book_cover_not_available)
            book!!.imageLinks!!.small != null -> glideLoad(book!!.imageLinks!!.small!!)
            book!!.imageLinks!!.large != null -> glideLoad(book!!.imageLinks!!.large!!)
            book!!.imageLinks!!.extraLarge != null -> glideLoad(book!!.imageLinks!!.extraLarge!!)
            book!!.imageLinks!!.thumbnail != null -> glideLoad(book!!.imageLinks!!.thumbnail!!)
            book!!.imageLinks!!.smallThumbnail != null -> glideLoad(book!!.imageLinks!!.smallThumbnail!!)
            else -> binding.ivBookCover.setImageResource(R.drawable.ic_book_cover_not_available)
        }
        // fill title
        binding.tvBookTitle.text = book!!.title
        // fill authors
        var authors = ""
        for(author in book!!.authors!!)
            authors += "$author, "
        binding.tvBookAuthor.text = authors.dropLast(2)
        // fill rating
        if (book!!.averageRating != null)
            binding.tvRating.text = book!!.averageRating.toString()
        // fill category
        if (book!!.categories != null){
            var categories = ""
            for (category in book!!.categories!!)
                categories += "$category,\n"
            binding.tvCategory.text = categories.dropLast(2)
        }
        // fill page count
        if (book!!.pageCount != null)
            binding.tvPageCount.text = book!!.pageCount.toString()
        // fill description
        if (book!!.description != null)
            binding.tvDescriptionText.text = book!!.description + "\n"
    }

    private fun glideLoad(url: String){
        try { Glide.with(this).load(url).into(binding.ivBookCover) }
        catch (e: Exception) { e.printStackTrace() }
    }
}