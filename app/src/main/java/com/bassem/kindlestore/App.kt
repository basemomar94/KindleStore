package com.bassem.kindlestore

import android.app.Application
import android.util.Log
import com.facebook.FacebookSdk
import com.facebook.appevents.AppEventsLogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FacebookSdk.setClientToken("485cd2c60742c2177283895de4bec943");
        FacebookSdk.sdkInitialize(applicationContext)
        AppEventsLogger.activateApp(this)
        println(FacebookSdk.getApplicationSignature(this))
        Log.d("FB", FacebookSdk.getApplicationSignature(this).toString())
    }
}