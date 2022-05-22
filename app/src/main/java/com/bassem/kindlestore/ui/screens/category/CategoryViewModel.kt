package com.bassem.kindlestore.ui.screens.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.entities.Book
import com.bassem.kindlestore.repo.HomeRepository

class CategoryViewModel : ViewModel() {
    val homeRepo = HomeRepository()
    val booksLiveData = MutableLiveData<MutableList<Book>>()


    fun fetechBooks() {
        homeRepo.fetchBooks(booksLiveData)
    }
}