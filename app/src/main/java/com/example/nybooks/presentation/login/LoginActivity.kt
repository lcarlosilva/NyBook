package com.example.nybooks.presentation.login

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.nybooks.R
import com.example.nybooks.presentation.books.BooksActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            this.startActivity(BooksActivity.getIntent(this))
        }
    }
}