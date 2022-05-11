package com.bassem.kindlestore.ui.home

import androidx.lifecycle.MutableLiveData
import com.bassem.kindlestore.entities.BookClass
import com.google.firebase.firestore.FirebaseFirestore

class HomeRepository {

    val db = FirebaseFirestore.getInstance()


    fun fetchBooks(liveData: MutableLiveData<MutableList<BookClass>>) {
        val _booksList: MutableList<BookClass> = mutableListOf()

        db.collection("Books").get().addOnCompleteListener {
            if (it.isSuccessful) {
                for (book in it.result.documentChanges) {
                    _booksList.add(book.document.toObject(BookClass::class.java))

                }
                liveData.postValue(_booksList)
            }
        }
    }


}