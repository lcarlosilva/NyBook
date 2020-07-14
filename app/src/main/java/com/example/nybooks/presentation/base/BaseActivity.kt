package com.example.nybooks.presentation.base


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

    protected fun setupToolbar(toolbar: Toolbar, titleIdRes: Int, hasBackBtn: Boolean = false) {
        toolbar.title = getString(titleIdRes)
        setSupportActionBar(toolbar)
        if (hasBackBtn) supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

}