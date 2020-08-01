package com.example.nybooks.presentation.login

sealed class LoginViewState {
    object Success: LoginViewState()
    object Loading: LoginViewState()
    data class Error(val message: String): LoginViewState()
}