package com.example.nybooks.data.repository

import com.example.nybooks.data.Response
import com.example.nybooks.data.model.Token
import com.example.nybooks.data.model.User

interface LoginRepository {
    fun auth(user: User, callBack: (response: Response<Token>) -> Unit)
}