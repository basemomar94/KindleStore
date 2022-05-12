package com.bassem.kindlestore.ui.screens.home

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bassem.kindlestore.R
import com.bassem.kindlestore.adapters.BooksAdapter
import com.bassem.kindlestore.databinding.FragmentHomeBinding
import com.bassem.kindlestore.entities.Book

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

        gettingData()


    }


    private fun recycleSetup(
        recyclerView: RecyclerView,
        context: Context,
        list: MutableList<Book>
    ) {
        booksAdapter = BooksAdapter(list, context, this)
        recyclerView.apply {
            adapter = booksAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)

        }

    }

    override fun viewItem(book: Book, position: Int) {
        viewBook(book, position)

    }

    private fun viewBook(book: Book, position: Int) {
        booksAdapter?.notifyItemChanged(position)
        val bundle = Bundle()
        bundle.putSerializable("book", book)
        val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
        navController.navigate(R.id.action_homeFragment_to_bookDisplayFragment, bundle)

    }

    private fun gettingData() {
        viewModel?.fetchBooks()
        viewModel?.booksLiveData?.observe(viewLifecycleOwner) {
            if (it != null) {
                recycleSetup(binding?.recycleNovel!!, requireContext(), it)
                recycleSetup(binding?.recycleEconomics!!, requireContext(), it)
                recycleSetup(binding?.recyclePhilosophy!!, requireContext(), it)
                recycleSetup(binding?.recyleHistory!!, requireContext(), it)
                recycleSetup(binding?.recyclePlotics!!, requireContext(), it)
                recycleSetup(binding?.recycleTrend!!, requireContext(), it)

                endLoading()
            }
        }
    }

    private fun endLoading() {
        binding?.apply {
            loading.visibility = View.GONE
            recycleLayout.visibility = View.VISIBLE

        }
    }


}