package com.dteti.bookapp.data.api

import androidx.lifecycle.MutableLiveData
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.data.model.BooksApiResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BooksRepository {
    var client = BookClient.instance
    fun getBooksByTopic(topic: String): MutableLiveData<MutableList<Book>> {
        val bookListLiveData = MutableLiveData<MutableList<Book>>()
        val bookList: MutableList<Book> = mutableListOf()
        client.getBooksByTopic("subject:$topic").enqueue(
            object: Callback<BooksApiResponse>{
                override fun onResponse(
                    call: Call<BooksApiResponse>,
                    response: Response<BooksApiResponse>
                ){
                    response.body().let {
                        if (!it!!.items.isNullOrEmpty())
                            it.items.forEach { item -> bookList.add(item.book) }
                    }
                    bookListLiveData.value = bookList
                }
                override fun onFailure(call: Call<BooksApiResponse>, t: Throwable){
                    t.printStackTrace()
                }
            }
        )
        return bookListLiveData
    }
}