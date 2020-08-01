package com.example.nybooks.ext

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T: ViewModel>AppCompatActivity.viewModel() = lazy { ViewModelProviders.of(this).get(T::class.java) }

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

