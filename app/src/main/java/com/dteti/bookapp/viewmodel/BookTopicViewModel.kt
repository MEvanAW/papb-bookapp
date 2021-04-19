package com.dteti.bookapp.viewmodel

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.dteti.bookapp.R
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.view.adapter.BookAdapter

class BookTopicViewModel(act: Activity) : ViewModel() {
    internal val bookAdapter = BookAdapter(
            arrayListOf(
                    Book("Sapiens", "Yuval Noah Harari", "4.4", R.drawable.sapiens),
                    Book("The Land of Five Towers", "Ahmad Fuadi", "4.1", R.drawable.the_land_of_five_towers),
                    Book("Laskar Pelangi", "Andrea Hirata", "4.2", R.drawable.laskar_pelangi)
            ), act)
}