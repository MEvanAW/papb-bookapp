package com.dteti.bookapp.viewmodel

import android.app.Activity
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.dteti.bookapp.R
import com.dteti.bookapp.data.api.RetrofitClient
import com.dteti.bookapp.data.model.QuotesJSON
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object QuoteViewModel {

    private val backgroundArray = arrayListOf(R.drawable.ic_group_171, R.drawable.ic_group_172, R.drawable.ic_group_173, R.drawable.ic_group_174)
    var quotesGenerated : String = ""

    fun quotesToView(clBackground : ConstraintLayout, act : Activity) {
        getQuotes(act)
        clBackground.setBackgroundResource(backgroundArray[(0..3).random()])
        try {
            if (quotesGenerated.length < 250) {
                getQuotes(act)
            }
        } catch (e :Exception) {
            e.printStackTrace()
        }
    }

    fun getQuotes(act : Activity) {
        RetrofitClient.instance.getApi().enqueue(
            object : Callback<QuotesJSON> {
                override fun onResponse(
                    call: Call<QuotesJSON>,
                    response: Response<QuotesJSON>
                ) {
                    response.body().let { quotesGenerated = it!!.quotes[0].text }
                }

                override fun onFailure(call: Call<QuotesJSON>, t: Throwable) {
                    Toast.makeText(act, "Quotes unavailable while you're offline", Toast.LENGTH_SHORT).show()
                    t.printStackTrace()
                }
            }
        )
    }
}