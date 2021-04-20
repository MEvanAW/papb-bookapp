package com.dteti.bookapp.view.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book

class BookAdapter(internal var bookList: MutableList<Book>, act : Activity) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    var act = act

    inner class ViewHolder(view: View) :RecyclerView.ViewHolder(view) {
        val title = itemView.findViewById<TextView>(R.id.tvTitle)
        val author = itemView.findViewById<TextView>(R.id.tvAuthor)
        val rating = itemView.findViewById<TextView>(R.id.tvRating)
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
        holder.rating.text = book.averageRating.toString()
        holder.image.setImageResource(R.drawable.the_land_of_five_towers)

        holder.bind(book)

        /*try {
            Glide.with(act).load(books.bookCover).into(holder.image)
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
    }

    override fun getItemCount(): Int = bookList.size

}