package com.example.parrot.modules.authentication.business

import android.util.Patterns
import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
//import com.example.parrot.core.network.BaseNetwork.Companion.HEADER_CLIENT
import com.example.parrot.modules.authentication.database.AuthenticationDatabase
import com.example.parrot.modules.authentication.model.SessionAuthentication
import com.example.parrot.modules.authentication.network.AuthenticationNetwork

//restrições das entradas para login e método para realizar login onde AuthenticationNetwork.requestLogin é chamado
object AuthenticationBusiness {

    fun isEmailValid(email: String?): Boolean {
        return !email.isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isPasswordValid(password: String?): Boolean {
        return !password.isNullOrEmpty()
    }

    fun isNameValid(name: String?): Boolean {
        return !name.isNullOrEmpty() && name.length > 2
    }

    fun isUsernameValid(username: String?): Boolean {
        return !username.isNullOrEmpty()
    }

    fun hasUserLogged(): Boolean {

        return AuthenticationDatabase.getSessionAuthentication() != null

    }

    fun registerUser(
            nome: String,
            username: String,
            email: String,
            password: String,
            onSuccess: () -> Unit,
            onError: (message: String) -> Unit
    ) {

        AuthenticationNetwork.requestRegisterUser(nome, username, email, password,
                onSuccess = {
                    onSuccess()
                },
                onError = {
                    onError("Falha ao cadastrar usuário")
                })
    }

    fun doLogin(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onError: (message: String) -> Unit
    ) {

        AuthenticationNetwork.requestLogin(email, password,
                onSuccess = { response ->

                    val sessionAuth = SessionAuthentication().apply {
                        token = response.headers() [BaseNetwork.TOKEN] ?: ""
                        user = response.body()
                    }

                    if (sessionAuth.token.isEmpty()) {
                        onError("Falha ao autenticar usuário")
                        return@requestLogin
                    }

                    AuthenticationDatabase.saveSession(sessionAuth)

                    onSuccess()

                },
                onError = {
                    onError("Falha no login")
                })
    }

    fun doLogout() {

        AuthenticationDatabase.clearAppData()

    }
}