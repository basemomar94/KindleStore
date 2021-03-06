package com.bassem.kindlestore.ui.screens.bookdisplay

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassem.kindlestore.R
import com.bassem.kindlestore.adapters.SimilarAdapter
import com.bassem.kindlestore.databinding.BookdisplayFragmentBinding
import com.bassem.kindlestore.entities.Book
import com.bumptech.glide.Glide
import java.lang.Exception

class BookDisplayFragment : Fragment(R.layout.bookdisplay_fragment),
    SimilarAdapter.expandInterface {
    private var binding: BookdisplayFragmentBinding? = null
    private var viewModel: DisplayViewModel? = null
    private var displayedBook: Book? = null
    private var booksAdapter: SimilarAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayedBook = this.arguments?.getSerializable("book") as Book
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
        viewModel = ViewModelProvider(this)[DisplayViewModel::class.java]
        displayedBook?.let {
            updateUi(it)
            downloadBook(it)
            seeMore(it.category)

        }
        fetchSimilarBooks()


    }


    private fun updateUi(book: Book) {
        binding?.apply {
            Glide.with(requireContext()).load(book.photo).into(bookImg)
            booktitle.text = book.title
            bookdisc.text = book.description
            bookauthor.text = book.author
        }

    }


    private fun downloadBook(book: Book) {
        binding?.button?.setOnClickListener {
            makeToast("???????? ??????????????")
            try {
                viewModel?.downloadBook(requireActivity(), book.link.toString(), book.title)
                //  downloadFromDrive(book.link!!)

            } catch (e: Exception) {
                makeToast("???????? ??????????????..???????? ?????? ????????")
                Log.d("debug", e.message.toString())
            }

        }
    }

    private fun recycleSetup(
        recyclerView: RecyclerView,
        context: Context,
        list: MutableList<Book>
    ) {
        booksAdapter = SimilarAdapter(list, context, this)
        recyclerView.apply {
            adapter = booksAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

        }

    }

    private fun fetchSimilarBooks() {
        viewModel?.apply {
            fetchSimilarBooks()
            similarLiveData.observe(viewLifecycleOwner) {
                recycleSetup(binding?.similarRv!!, requireContext(), it)

            }
        }

    }


    private fun makeToast(text: String) {
        Toast.makeText(requireContext(), text, Toast.LENGTH_SHORT)
            .show()
    }

    override fun viewItem(item: Book, category: String) {
        updateUi(item)
        fetchSimilarBooks()
        downloadBook(item)
    }

    private fun seeMore(category: String) {
        binding?.moreDisplay?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("category", category)
            val navController =
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
            navController.navigate(R.id.action_bookDisplayFragment_to_categoryFragment, bundle)
        }
    }

    fun downloadFromDrive(DRIVE_URL: String) {
        val driveIntent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(DRIVE_URL)
        )//Link can have any data link .apk, .mp4 ..
        val browserChooserIntent = Intent.createChooser(driveIntent, "Choose browser")
        startActivity(browserChooserIntent)
    }


}