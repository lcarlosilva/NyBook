package com.example.nybooks.data

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.example.nybooks.presentation.App
import com.example.nybooks.presentation.login.LoginActivity
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class NYAuthenticator: Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        App.context?.run {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
        return null
    }
}