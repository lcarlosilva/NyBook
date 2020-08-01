package com.example.nybooks.data

import android.content.Context
import com.example.nybooks.presentation.App

class UserStorage(context: Context) {

    companion object{
        val instance: UserStorage by lazy { UserStorage(App.context!!) }
    }

    private val sharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
    var token: String
        get() = sharedPreferences.getString("token", null) ?: ""
        set(value) {
            sharedPreferences.edit().putString("token", value).apply()
        }
}