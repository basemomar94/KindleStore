package com.bassem.kindlestore.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.entities.BookClass

class HomeViewModel : ViewModel() {
    val repo = HomeRepository()
    val booksLiveData = MutableLiveData<MutableList<BookClass>>()

    fun fetchBooks() {
        repo.fetchBooks(booksLiveData)
    }
}