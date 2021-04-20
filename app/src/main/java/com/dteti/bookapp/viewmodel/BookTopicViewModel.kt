package com.dteti.bookapp.viewmodel

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.dteti.bookapp.data.api.BookClient
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BooksApiResponse
import com.dteti.bookapp.view.adapter.BookAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookTopicViewModel() : ViewModel() {
    var bookList: MutableList<Book> = mutableListOf()
    fun getBookAdapterByTopic(topic: String, act: Activity): BookAdapter{
        val request = BookClient.instance.getBooksByTopic("subject:$topic")
        Log.d("REQUESTLOG", request.request().toString())
        BookClient.instance.getBooksByTopic("subject:$topic").enqueue(
            object: Callback<BooksApiResponse>{
                override fun onResponse(
                    call: Call<BooksApiResponse>,
                    response: Response<BooksApiResponse>
                ){
                    response.body().let {
                        if (!it!!.items.isNullOrEmpty())
                            it.items.forEach { item -> bookList.add(item.book) }
                    }
                    Log.d("ONRESPONSE: CODE", response.code().toString())
                    Log.d("ONRESPONSE: BODY", response.body().toString())
                    Log.d("ONRESPONSE: BOOKLIST", bookList.toString())
                }
                override fun onFailure(call: Call<BooksApiResponse>, t: Throwable){
                    t.printStackTrace()
                    Log.d("RESPONSELOG", "onFailure")
                }
            }
        )
        Log.d("RETURN", bookList.toString())
        return BookAdapter(bookList, act)
    }
    /*internal val bookAdapter = BookAdapter(
            arrayListOf(
                    Book("Sapiens", "Yuval Noah Harari", "4.4", R.drawable.sapiens),
                    Book("The Land of Five Towers", "Ahmad Fuadi", "4.1", R.drawable.the_land_of_five_towers),
                    Book("Laskar Pelangi", "Andrea Hirata", "4.2", R.drawable.laskar_pelangi)
            ), act)*/
}