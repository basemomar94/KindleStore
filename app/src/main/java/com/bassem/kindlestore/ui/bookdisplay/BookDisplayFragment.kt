package com.bassem.kindlestore.ui.bookdisplay

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bassem.kindlestore.R
import com.bassem.kindlestore.databinding.BookdisplayFragmentBinding
import com.bassem.kindlestore.entities.Book
import com.bumptech.glide.Glide

class BookDisplayFragment : Fragment(R.layout.bookdisplay_fragment) {
    var binding: BookdisplayFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BookdisplayFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUi(gettingUiData())

    }


    private fun updateUi(book: Book) {
        binding?.apply {
            Glide.with(requireContext()).load(book.photo).into(bookImg)
            booktitle.text = book.title
            bookdisc.text = book.description
        }

    }

    private fun gettingUiData() = this.arguments?.getSerializable("book") as Book


}