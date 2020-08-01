package com.example.nybooks.presentation.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.nybooks.R
import com.example.nybooks.ext.hide
import com.example.nybooks.ext.show
import com.example.nybooks.ext.viewModel
import com.example.nybooks.presentation.books.BooksActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_login.setOnClickListener {
            viewModel.auth(txt_username.text.toString(), txt_password.text.toString())
        }

        viewModel.loginLiveData.observe(this, Observer {
            handleResult(it)
        })
    }

    private fun handleResult(state: LoginViewState) {
        when(state) {
            LoginViewState.Success -> {
                this.startActivity(BooksActivity.getIntent(this))
            }
            LoginViewState.Loading -> {
                progress_bar.show()
            }
            is LoginViewState.Error -> {
                progress_bar.hide()
                Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
            }
        }
    }
}