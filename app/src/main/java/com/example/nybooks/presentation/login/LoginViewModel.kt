package com.example.nybooks.presentation.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nybooks.data.Response
import com.example.nybooks.data.model.User
import com.example.nybooks.data.repository.LoginRepository
import com.example.nybooks.data.repository.impl.LoginRepositoryDataSource


class LoginViewModel(private val repository: LoginRepository = LoginRepositoryDataSource()): ViewModel() {

    val loginLiveData = MutableLiveData<LoginViewState>()

    fun auth(name: String, password: String){
        loginLiveData.postValue(LoginViewState.Loading)
        repository.auth(User(name, password)) { result ->
            val state = when(result) {
                is Response.Success -> LoginViewState.Success
                is Response.Error -> LoginViewState.Error(result.throwable.message.orEmpty())
            }
            loginLiveData.postValue(state)
        }
    }
}