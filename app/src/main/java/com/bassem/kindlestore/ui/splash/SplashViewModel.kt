package com.bassem.kindlestore.ui.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.repo.LoginRepository

class SplashViewModel : ViewModel() {
    var isSign = MutableLiveData<Boolean>()
    val repo = LoginRepository()
    fun isSign() {
        isSign.postValue(repo.checkSignin())
        Log.d("Sign", repo.checkSignin().toString())
        Log.d("Sign", repo.getUserId().toString())


    }
}