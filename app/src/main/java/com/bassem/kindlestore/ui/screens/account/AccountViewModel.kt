package com.bassem.kindlestore.ui.screens.account

import android.app.Activity
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.repo.LoginRepository

class AccountViewModel : ViewModel() {
    private val repo = LoginRepository()
    fun logOut() {
        repo.logOut()
    }
}