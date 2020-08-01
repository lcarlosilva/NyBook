package com.example.nybooks.data.repository.impl

import com.example.nybooks.data.NYApiService
import com.example.nybooks.data.Response
import com.example.nybooks.data.UserStorage
import com.example.nybooks.data.model.Token
import com.example.nybooks.data.model.User
import com.example.nybooks.data.repository.LoginRepository
import java.lang.IllegalArgumentException

class LoginRepositoryDataSource: LoginRepository {

    private val user = User("testeNome", "testeSenha")

    override fun auth(user: User, callBack: (response: Response<Token>) -> Unit) {
        if (user == this.user) {
            val data = Token("S9QbIZ0OjMUbABKe6pnBmzhfGxjLN8Ob", "teste")
            UserStorage.instance.token = data.accessToken
            callBack(Response.Success(data))
        } else {
            callBack(Response.Error(IllegalArgumentException("Login inválido!")))
        }
    }
}