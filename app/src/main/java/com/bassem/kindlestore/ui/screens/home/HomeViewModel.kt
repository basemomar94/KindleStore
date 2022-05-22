package com.bassem.kindlestore.ui.screens.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.entities.Book
import com.bassem.kindlestore.repo.HomeRepository

class HomeViewModel : ViewModel() {
    private val repo = HomeRepository()
    val booksLiveData = MutableLiveData<MutableList<Book>>()

    fun fetchBooks() {
        repo.fetchBooks(booksLiveData)
    }


}