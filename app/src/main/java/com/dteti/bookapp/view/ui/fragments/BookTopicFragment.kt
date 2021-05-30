package com.dteti.bookapp.view.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dteti.bookapp.view.adapter.BookAdapter
import com.dteti.bookapp.data.model.Book
import com.dteti.bookapp.databinding.FragmentBookTopicBinding
import com.dteti.bookapp.view.ui.activities.BookDetailActivity
import com.dteti.bookapp.viewmodel.BookTopicViewModel

// The fragment initialization parameter(s)
private const val ARG_TOPIC = "Topic"
private const val ARG_TV_TEXT = "TvText"
private const val ARG_BOOK = "Book"

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
    private var tvText: String? = null
    private var book: Book? = null

    // Adapter
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            topic = it.getString(ARG_TOPIC)
            tvText = it.getString(ARG_TV_TEXT)
            book = it.getParcelable(ARG_BOOK)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBookTopicBinding.inflate(inflater, container, false)
        binding.tvBookTopic.text = tvText
        binding.rvBookTopic.setHasFixedSize(true)
        bookTopicViewModel = BookTopicViewModel()
        bookAdapter = BookAdapter(mutableListOf(), requireActivity(), false)
        bookTopicViewModel.getBooksByTopic(topic!!).observe({ lifecycle }, { bookList ->
            run {
                bookAdapter.bookList = bookList
                if (book != null)
                    bookAdapter.bookList.filter { toRemove: Book -> toRemove.title.equals(book!!.title) }.forEach { bookAdapter.bookList.remove(it) }
                binding.rvBookTopic.adapter!!.notifyDataSetChanged()
            }
        })
        bookAdapter.callableOnClick(object: BookAdapter.IOnBookClicked{
            override fun onBookClicked(book: Book){
                val intent = Intent(context, BookDetailActivity::class.java)
                intent.putExtra("BOOK_DATA", book)
                startActivity(intent)
            }
            override fun onReadLater(book: Book) {
                bookTopicViewModel.insertBookAsToRead(book)
                Toast.makeText(
                    requireContext(),
                    book.title + " is added to be read later.",
                    Toast.LENGTH_LONG).show()
            }
        })
        binding.rvBookTopic.adapter = bookAdapter
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
         * @param tvText Text to be shown for the TextView.
         * @return A new instance of fragment BookTopicFragment.
         */
        @JvmStatic
        fun newInstance(topic: String, tvText: String, book: Book?) =
            BookTopicFragment().apply {
                arguments = Bundle().apply{
                    putString(ARG_TOPIC, topic)
                    putString(ARG_TV_TEXT, tvText)
                    putParcelable(ARG_BOOK, book)
                }
            }
    }
}