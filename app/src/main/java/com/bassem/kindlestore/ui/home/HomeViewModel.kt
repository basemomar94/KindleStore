package com.bassem.kindlestore.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.entities.Book

class HomeViewModel : ViewModel() {
    private val repo = HomeRepository()
    val booksLiveData = MutableLiveData<MutableList<Book>>()

    fun fetchBooks() {
        repo.fetchBooks(booksLiveData)
    }


}