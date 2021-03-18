package com.dteti.bookapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// The fragment initialization parameter(s)
private const val ARG_TOPIC = "Topic"

/**
 * A simple [Fragment] subclass.
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
        return inflater.inflate(R.layout.fragment_book_topic, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookTopicFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(topic: String) =
                BookTopicFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_TOPIC, topic)
                    }
                }
    }
}