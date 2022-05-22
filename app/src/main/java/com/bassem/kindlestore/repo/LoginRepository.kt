package com.bassem.kindlestore.repo

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth

class LoginRepository {

    private val auth = FirebaseAuth.getInstance()


    fun continueFacebook(
        facebookButton: LoginButton, context: Context, result: MutableLiveData<Boolean>,call: CallbackManager
    ) {
        facebookButton.registerCallback(call, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessTocken(loginResult.accessToken, context,result)
                Log.d("FB", "facebook:onSuccess:$loginResult")
                //  handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Log.d("FB", "facebook:onCancel")
                // ...
            }

            override fun onError(error: FacebookException) {
                Log.d("FB", "facebook:onError", error)
                // ...
            }
        })
        // ...


    }

    private fun handleFacebookAccessTocken(
        accessToken: AccessToken,
        context: Context,
        result: MutableLiveData<Boolean>
    ) {
        val credentials = FacebookAuthProvider.getCredential(accessToken.token)
        auth?.signInWithCredential(credentials)?.addOnFailureListener {
            Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            result.postValue(false)

        }?.addOnSuccessListener {
            val mail = it.user?.email
            Log.d("FB", mail.toString())
            result.postValue(true)

        }
    }
}