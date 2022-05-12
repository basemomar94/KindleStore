package com.bassem.kindlestore.ui.screens.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassem.kindlestore.R
import com.bassem.kindlestore.adapters.BooksAdapter
import com.bassem.kindlestore.databinding.FragmentCategoryBinding
import com.bassem.kindlestore.databinding.FragmentHomeBinding
import com.bassem.kindlestore.entities.Book
import com.bassem.kindlestore.ui.screens.home.HomeViewModel

class CategoryFragment : Fragment(R.layout.fragment_category), BooksAdapter.expandInterface {
    var binding: FragmentCategoryBinding? = null
    private var viewModel: CategoryViewModel? = null
    private var booksAdapter: BooksAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CategoryViewModel::class.java]
        gettingBooks()

    }


    private fun recycleSetup(
        recyclerView: RecyclerView,
        context: Context,
        list: MutableList<Book>
    ) {
        booksAdapter = BooksAdapter(list, context, this)
        recyclerView.apply {
            adapter = booksAdapter
            layoutManager = GridLayoutManager(context, 2)
            setHasFixedSize(true)

        }

    }

    override fun viewItem(book: Book, position: Int) {

    }


    private fun gettingBooks(){
        viewModel?.fetechBooks()
        viewModel?.booksLiveData?.observe(viewLifecycleOwner){
            recycleSetup(binding?.categoryRv!!,requireContext(),it)

        }
    }


}