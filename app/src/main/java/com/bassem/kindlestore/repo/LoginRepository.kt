package com.bassem.kindlestore.repo

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.bassem.kindlestore.entities.User
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.facebook.login.widget.LoginButton
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore

class LoginRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()
    private val googleClient: GoogleSignInClient? = null


    fun continueFacebook(
        facebookButton: LoginButton,
        context: Context,
        result: MutableLiveData<Boolean>,
        call: CallbackManager
    ) {
        facebookButton.registerCallback(call, object :
            FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                handleFacebookAccessToken(loginResult.accessToken, context, result)
            }

            override fun onCancel() {
            }

            override fun onError(error: FacebookException) {
            }
        })


    }

    private fun handleFacebookAccessToken(
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
            it.user?.let { it1 -> checkifUserexsit(it1) }

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

    private fun addUserDatabase(user: User) {
        db.collection("users").add(user)
    }

    fun checkSigning() = auth.currentUser?.uid != null
    fun getUserId() = auth.currentUser?.uid
    fun logOut() {
        auth.signOut()
        LoginManager.getInstance().logOut()

    }

    private fun checkifUserexsit(firebaseuser: FirebaseUser) {
        db.collection("users").whereEqualTo("mail", firebaseuser.email).get()
            .addOnCompleteListener {
                Log.d("mail", it.result.isEmpty.toString())

                if (it.result.isEmpty) {
                    addUserDatabase(getUserData(firebaseuser))
                }
            }

    }

    fun continuewithGoogle(context: Context, activity: Activity) {
        Log.d("REQ","Google")

        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
            .requestIdToken("156449307657-560fvrulm2bfrl07b8l42kidnvpmr4a5.apps.googleusercontent.com")
            .requestEmail().build()
        Log.d("REQ",options.toString())


        val signInClient = GoogleSignIn.getClient(context, options)
        Log.d("REQ",signInClient.toString())

        signInClient.signInIntent.also {
            activity.startActivityForResult(it, 101)
        }




    }

    fun googleAuthFirebase(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        auth.signInWithCredential(credentials)

    }


}