package com.dteti.bookapp.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dteti.bookapp.R
import com.dteti.bookapp.data.api.QuoteClient
import com.dteti.bookapp.data.model.QuotesJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuoteViewModel : ViewModel() {

    private val backgroundArray = arrayListOf(R.drawable.ic_group_171, R.drawable.ic_group_172, R.drawable.ic_group_173, R.drawable.ic_group_174)
    var quotesGenerated= MutableLiveData<String>()
    var background = MutableLiveData<Int>()

    init {
        quotesGenerated.value = ""
        background.value = backgroundArray[0]
    }

    fun quotesToView(act : Activity) {
        getQuotes(act)
        background.value = backgroundArray[(0..3).random()]
        try {
            if (quotesGenerated.value!!.length < 250) {
                getQuotes(act)
            }
        } catch (e :Exception) {
            e.printStackTrace()
        }
    }

    fun getQuotes(act : Activity) {
        QuoteClient.instance.getApi().enqueue(
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