package com.bassem.kindlestore.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bassem.kindlestore.R
import com.bassem.kindlestore.adapters.BooksAdapter
import com.bassem.kindlestore.databinding.FragmentHomeBinding
import com.bassem.kindlestore.entities.BookClass

class HomeFragment : Fragment(R.layout.fragment_home), BooksAdapter.expandInterface {

    private var viewModel: HomeViewModel? = null
    private var binding: FragmentHomeBinding? = null
    private var booksAdapter: BooksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel?.fetchBooks()



        viewModel?.booksLiveData?.observe(viewLifecycleOwner) {
            recycleSetup(requireContext(), it)
            Log.d("Debug", it.size.toString())
        }

    }


    private fun recycleSetup(context: Context, list: MutableList<BookClass>) {
        booksAdapter = BooksAdapter(list, context, this)
        binding?.recycle?.apply {
            adapter = booksAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

        }

    }

    override fun viewItem(item: String, category: String, position: Int) {

    }


}