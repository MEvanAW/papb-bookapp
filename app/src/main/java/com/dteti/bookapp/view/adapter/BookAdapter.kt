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

class BookAdapter(private val list: ArrayList<Book>, act : Activity) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

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
        val books : Book = list.get(position)

        holder.title.text = books.title
        holder.author.text = books.author
        holder.rating.text = books.rating
        holder.image.setImageResource(books.bookCover)

        holder.bind(books)

        /*try {
            Glide.with(act).load(books.bookCover).into(holder.image)
        } catch (e: Exception) {
            e.printStackTrace()
        }*/
    }

    override fun getItemCount(): Int = list.size

}