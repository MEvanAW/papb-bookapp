package com.dteti.bookapp.view.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dteti.bookapp.view.adapter.BookAdapter
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.databinding.FragmentBookTopicBinding
import com.dteti.bookapp.view.ui.activities.BookDetailActivity
import com.dteti.bookapp.viewmodel.BookTopicViewModel

// The fragment initialization parameter(s)
private const val ARG_TOPIC = "Topic"

/**
 * A simple [Fragment] subclass to hold a book topic.
 * Use the [BookTopicFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookTopicFragment : Fragment() {
    // View Model
    private lateinit var bookTopicViewModel: BookTopicViewModel

    // Data Binding
    private var _binding: FragmentBookTopicBinding? = null
    private val binding get() = _binding!!

    // Parameter
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
        _binding = FragmentBookTopicBinding.inflate(inflater, container, false)
        binding.tvBookTopic.text = topic
        binding.rvBookTopic.setHasFixedSize(true)
        bookTopicViewModel = BookTopicViewModel(requireActivity())
        binding.rvBookTopic.adapter = bookTopicViewModel.bookAdapter
        bookTopicViewModel.bookAdapter.callableOnClick(object: BookAdapter.OnBookCLicked{
            override fun onBookClicked(data: Book){
                val intent = Intent(context, BookDetailActivity::class.java)
                startActivity(intent)
            }
        })
        return binding.root
    }

    override fun onDestroyView(){
        super.onDestroyView()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param topic The books topic.
         * @param act The activity of the fragment.
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