package com.dteti.bookapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// The fragment initialization parameter(s)
private const val ARG_TOPIC = "Topic"

/**
 * A simple [Fragment] subclass to hold a book topic.
 * Use the [BookTopicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookTopicFragment : Fragment() {
    private var topic: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            topic = it.getString(ARG_TOPIC)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_book_topic, container, false)
        val tvBookTopic: TextView = rootView.findViewById(R.id.tv_book_topic)
        tvBookTopic.text = topic
        val adapter = BookAdapter(
            arrayListOf(
                Book("Sapiens", "Yuval Noah Harari", "4.4", R.drawable.sapiens),
                Book("The Land of Five Towers", "Ahmad Fuadi", "4.1", R.drawable.the_land_of_five_towers),
                Book("Laskar Pelangi", "Andrea Hirata", "4.2", R.drawable.laskar_pelangi)
            ), this.requireActivity())
        val rvBookTopic: RecyclerView = rootView.findViewById(R.id.rv_book_topic)
        rvBookTopic.setHasFixedSize(true)
        rvBookTopic.adapter = adapter
        adapter.callableOnClick(object: BookAdapter.OnBookCLicked{
            override fun onBookClicked(data: Book){
                val intent = Intent(context, BookDetailActivity::class.java)
                startActivity(intent)
            }
        })
        return rootView
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param topic The Books Topic.
         * @return A new instance of fragment BookTopicFragment.
         */
        @JvmStatic
        fun newInstance(topic: String) =
                BookTopicFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TOPIC, topic)
                    }
                }
    }
}