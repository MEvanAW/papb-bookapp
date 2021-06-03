package com.dteti.bookapp.view.adapter

import android.app.Activity
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book

class BookAdapter(internal var bookList: MutableList<Book>, private val act : Activity, private val isProfile: Boolean) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        // initiation of views inside custom_cardview
        val title: TextView = itemView.findViewById(R.id.tvTitle)
        val author: TextView = itemView.findViewById(R.id.tvAuthor)
        val image: ImageView = itemView.findViewById(R.id.ivBookTrue)
        val readLaterIcon: ImageView = itemView.findViewById(R.id.ivReadLater)

        fun bind(book: Book) {
            image.setOnClickListener { onClicked?.onBookClicked(book) }
            readLaterIcon.setOnClickListener{ onClicked?.onReadLater(book) }
        }
    }

    //set onClick variable
    private var onClicked : IOnBookClicked? = null

    //set interface
    interface  IOnBookClicked {
        fun onBookClicked(book: Book)
        fun onReadLater(book: Book)
    }

    fun callableOnClick(onBookClicked: IOnBookClicked) {
        this.onClicked = onBookClicked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.custom_cardview, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (isProfile)
            holder.readLaterIcon.visibility = View.GONE
        val book : Book = bookList[position]
        holder.title.text = book.title
        if (!book.authors.isNullOrEmpty())
            holder.author.text = book.authors[0]
        when {
            book.imageLinks == null -> holder.image.setImageResource(R.drawable.ic_book_cover_not_available)
            book.imageLinks.thumbnail != null -> glideLoad(book.imageLinks.thumbnail, holder)
            book.imageLinks.small != null -> glideLoad(book.imageLinks.small, holder)
            book.imageLinks.smallThumbnail != null -> glideLoad(book.imageLinks.smallThumbnail, holder)
            book.imageLinks.large != null -> glideLoad(book.imageLinks.large, holder)
            book.imageLinks.extraLarge != null -> glideLoad(book.imageLinks.extraLarge, holder)
            else -> holder.image.setImageResource(R.drawable.ic_book_cover_not_available)
        }
        holder.bind(book)
    }

    override fun getItemCount(): Int = bookList.size

    //load image with glide
    private fun glideLoad(url: String, holder: ViewHolder){
        try { Glide.with(act).load(url).into(holder.image) }
        catch (e: Exception) { e.printStackTrace() }
    }
}