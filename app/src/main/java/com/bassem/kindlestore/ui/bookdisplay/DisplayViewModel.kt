package com.bassem.kindlestore.ui.bookdisplay

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.entities.Book
import com.bassem.kindlestore.ui.home.HomeRepository

class DisplayViewModel : ViewModel() {
    private val repoDisplay = DisplayRepository()
    private val repoHome = HomeRepository()
    val similarLiveData = MutableLiveData<MutableList<Book>>()


    fun downloadBook(activity: Activity, link: String, filename: String) {
        repoDisplay.downloadBook(activity, link, filename)
    }

    fun fetchSimilarBooks() {
        repoHome.fetchBooks(similarLiveData)
    }

}