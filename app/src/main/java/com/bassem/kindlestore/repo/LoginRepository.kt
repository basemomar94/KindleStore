package com.bassem.kindlestore.repo

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.bassem.kindlestore.R
import com.bassem.kindlestore.entities.User
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore

class LoginRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()


    fun continueFacebook(
        facebookButton: LoginButton,
        context: Context,
        result: MutableLiveData<Boolean>,
        call: CallbackManager
    ) {
        facebookButton.registerCallback(call, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessTocken(loginResult.accessToken, context, result)
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
        auth.signInWithCredential(credentials).addOnFailureListener {
            Toast.makeText(context, it.message.toString(), Toast.LENGTH_SHORT).show()
            result.postValue(false)

        }.addOnSuccessListener {
            result.postValue(true)
            addUsertoDatabase(getUserData(it.user!!))

        }
    }

    private fun getUserData(firebaseuser: FirebaseUser): User {
        val user = User()
        user.mail = firebaseuser.email.toString()
        user.name = firebaseuser.displayName.toString()
        user.photo =
            "https://graph.facebook.com/" + firebaseuser.uid + "/picture?width=" + "100" + "&height=" + "100"
        return user
    }

    private fun addUsertoDatabase(user: User) {
        db.collection("users").add(user)
    }

    fun checkSignin() = auth.currentUser?.uid != null
    fun getUserId() = auth.currentUser?.uid
    fun logOut() {
        auth.signOut()
        LoginManager.getInstance().logOut()

    }
}