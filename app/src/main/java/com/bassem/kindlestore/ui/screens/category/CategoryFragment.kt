package com.bassem.kindlestore.ui.screens.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassem.kindlestore.R
import com.bassem.kindlestore.adapters.BooksAdapter
import com.bassem.kindlestore.databinding.FragmentCategoryBinding
import com.bassem.kindlestore.entities.Book

class CategoryFragment : Fragment(R.layout.fragment_category), BooksAdapter.expandInterface {
    var binding: FragmentCategoryBinding? = null
    private var viewModel: CategoryViewModel? = null
    private var booksAdapter: BooksAdapter? = null
    var category: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        category = this.arguments?.getString("category").toString()
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
        updateUi(category)

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


    private fun gettingBooks() {
        viewModel?.fetechBooks()
        viewModel?.booksLiveData?.observe(viewLifecycleOwner) {
            recycleSetup(binding?.categoryRv!!, requireContext(), it)

        }
    }


    private fun updateUi(category: String) {
        var categoryAr: String = ""
        when (category) {
            "bio" -> categoryAr = "سيرة ذاتية"
            "economics" -> categoryAr = "إقتصاد"
            "novel" -> categoryAr = "روايات"
            "history" -> categoryAr = "تاريخ"
            "philosophy" -> categoryAr = "فلسفة"
            "politics" -> categoryAr = "سياسي"
        }
        binding?.categoryTitle?.text = categoryAr


    }


}