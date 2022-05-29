package com.bassem.kindlestore.ui.signup

import android.app.Activity
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bassem.kindlestore.repo.LoginRepository
import com.facebook.CallbackManager
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class SignupViewModel : ViewModel() {

    val repo = LoginRepository()
    val successLogin = MutableLiveData<Boolean>()

    fun continueWithFacebook(button: LoginButton, context: Context, call: CallbackManager) {
        repo.continueFacebook(button, context, successLogin, call)
    }

    fun continueGoolge(context: Context, activity: Activity) {
        repo.continuewithGoogle(context, activity)
    }

    fun firebaseAuth(account: GoogleSignInAccount){
        repo.googleAuthFirebase(account)
    }


}