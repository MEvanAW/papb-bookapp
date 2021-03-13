package com.dteti.bookapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private var list : ArrayList<Book> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addList()

        val adapter = BookAdapter(list, this)
        val rvTrend = findViewById<RecyclerView>(R.id.rvTrend)
        val rvBestSell = findViewById<RecyclerView>(R.id.rvBestSell)
        rvTrend.setHasFixedSize(true)
        rvBestSell.setHasFixedSize(true)
        adapter.notifyDataSetChanged()
        rvTrend.adapter = adapter
        rvBestSell.adapter = adapter

        adapter.callableOnClick( object : BookAdapter.OnBookCLicked{
            override fun onBookClicked(data: Book) {
                moveAct()
            }
        })
    }

    fun moveAct() {
        val intent = Intent(this, BookDetailActivity::class.java)
        startActivity(intent)
    }

    fun drawableToBitmap(drawable: Int) : Bitmap {
        return BitmapFactory.decodeResource(resources, drawable)
    }

    fun addList() {
        var book1 = Book("Sapiens", "Yuval Noah Harari", "4.4", drawableToBitmap(R.drawable.sapiens))
        var book2 = Book("The Land of Five Towers", "Ahmad Fuadi", "4.1", drawableToBitmap(R.drawable.the_land_of_five_towers))
        var book3 = Book("Laskar Pelangi", "Andrea Hirata", "4.2", drawableToBitmap(R.drawable.laskar_pelangi))

        list.add(book1)
        list.add(book2)
        list.add(book3)
    }
}