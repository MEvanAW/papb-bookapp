package com.dteti.bookapp.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book

class BookAdapter(internal var bookList: MutableList<Book>, val act : Activity) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val author = itemView.findViewById<TextView>(R.id.tvAuthor)
        val image = itemView.findViewById<ImageView>(R.id.ivBookTrue)

        fun bind(position: Book) {
            itemView.setOnClickListener { onClicked?.onBookClicked(position) }
        }
    }

    private var onClicked : OnBookCLicked? = null

    interface  OnBookCLicked {
        fun onBookClicked(data: Book)
    }

    fun callableOnClick(onBookCLicked: OnBookCLicked) {
        this.onClicked = onBookCLicked
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
                R.layout.custom_cardview,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book : Book = bookList.get(position)

        holder.title.text = book.title
        if (!book.authors.isNullOrEmpty())
            holder.author.text = book.authors[0]
        when {
            book.imageLinks == null -> holder.image.setImageResource(R.drawable.ic_book_cover_not_available)
            book.imageLinks.thumbnail != null -> GlideLoad(book.imageLinks.thumbnail, holder)
            book.imageLinks.small != null -> GlideLoad(book.imageLinks.small, holder)
            book.imageLinks.smallThumbnail != null -> GlideLoad(book.imageLinks.smallThumbnail, holder)
            book.imageLinks.large != null -> GlideLoad(book.imageLinks.large, holder)
            book.imageLinks.extraLarge != null -> GlideLoad(book.imageLinks.extraLarge, holder)
            else -> holder.image.setImageResource(R.drawable.ic_book_cover_not_available)
        }

        holder.bind(book)

        /*try {
            Glide.with(act).load(books.bookCover).into(holder.image)
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
    }

    override fun getItemCount(): Int = bookList.size

    fun GlideLoad(url: String, holder: ViewHolder){
        try { Glide.with(act).load(url).into(holder.image) }
        catch (e: Exception) { e.printStackTrace() }
    }
}