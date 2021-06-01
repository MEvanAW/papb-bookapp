package com.dteti.bookapp.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BookStatus

class BookshelfAdapter(internal val bookshelf : MutableList<Book>, val act : Activity) : RecyclerView.Adapter<BookshelfAdapter.ViewHolder>() {

    internal var filteredBooks = bookshelf

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookshelfAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cardview_bookshelf, parent, false))
    }

    override fun onBindViewHolder(holder: BookshelfAdapter.ViewHolder, position: Int) {
        val book : Book = filteredBooks[position]
        holder.title.text = book.title
        if (!book.authors.isNullOrEmpty())
            holder.author.text = book.authors[0]
        if (book.bookStatus == BookStatus.TO_READ)
            holder.continueRead.text = "Start reading"
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

    override fun getItemCount(): Int {
        return filteredBooks.size
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        var title = itemView.findViewById<TextView>(R.id.tvTitleShelf)
        var author = itemView.findViewById<TextView>(R.id.tvAuthorShelf)
        var image = itemView.findViewById<ImageView>(R.id.ivBookShelf)
        var continueRead = itemView.findViewById<Button>(R.id.bt_continueShelf)
        var delete = itemView.findViewById<ImageButton>(R.id.bt_removeShelf)

        fun bind(book: Book) {
            continueRead.setOnClickListener { onClicked?.onItemClicked(book) }
            delete.setOnClickListener { onClicked?.onDeleteClicked(book) }
        }
    }

    //load image with glide
    fun glideLoad(url: String, holder: ViewHolder){
        try { Glide.with(act).load(url).into(holder.image) }
        catch (e: Exception) { e.printStackTrace() }
    }

    //set interface
    interface OnItemClicked {
        fun onItemClicked(book: Book)
        fun onDeleteClicked(book: Book)
    }

    //set onclick variable
    var onClicked : OnItemClicked? = null
    var onDeleteClicked : OnItemClicked? = null

    fun callableOnClick(onBookClicked: OnItemClicked) {
        this.onClicked = onBookClicked
        this.onDeleteClicked = onDeleteClicked
    }

}