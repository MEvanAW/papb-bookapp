package com.dteti.bookapp.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dteti.bookapp.R
import com.dteti.bookapp.data.api.QuoteClient
import com.dteti.bookapp.data.model.QuotesJSON
import com.dteti.bookapp.di.Dependencies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteViewModel : ViewModel() {

    //background array for quote view
    private val backgroundArray = arrayListOf(R.drawable.ic_group_171, R.drawable.ic_group_172, R.drawable.ic_group_173, R.drawable.ic_group_174)

    //setLiveData
    var quotesGenerated= MutableLiveData<String>()
    var background = MutableLiveData<Int>()

    init {
        quotesGenerated.value = ""
        background.value = backgroundArray[0]
    }

    fun getQuotes(act : Activity) {
        val quoteClient = Dependencies().quoteCl
        quoteClient.getApi().enqueue(
            object : Callback<QuotesJSON> {
                override fun onResponse(
                    call: Call<QuotesJSON>,
                    response: Response<QuotesJSON>
                ) {
                    response.body().let { if (it != null) quotesGenerated.value = it.quotes[0].text }
                }

                override fun onFailure(call: Call<QuotesJSON>, t: Throwable) {
                    Toast.makeText(act, "Quotes unavailable while you're offline", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            }
        )
    }
}