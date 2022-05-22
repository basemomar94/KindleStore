package com.bassem.kindlestore.repo

import androidx.lifecycle.MutableLiveData
import com.bassem.kindlestore.entities.Book
import com.google.firebase.firestore.FirebaseFirestore

class HomeRepository {

    val db = FirebaseFirestore.getInstance()


    fun fetchBooks(liveData: MutableLiveData<MutableList<Book>>) {
        val _booksList: MutableList<Book> = mutableListOf()

        db.collection("Books").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (book in it.result.documentChanges) {
                    _booksList.add(book.document.toObject(Book::class.java))

                }
                liveData.postValue(_booksList)
            }
        }
    }


}