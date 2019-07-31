package com.example.parrot

import android.util.Patterns
import com.example.parrot.BaseNetwork.Companion.HEADER_ACCESS_TOKEN
import com.example.parrot.BaseNetwork.Companion.HEADER_CLIENT

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

        val sessionAuth = SessionController.sessionAuthentication

        return SessionController.user != null && sessionAuth != null && sessionAuth.isSessionAuthenticated()
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

//                val user = response.body()
//
//                val sessionAuth = SessionAuthentication().apply {
//                    uid = user? ""
//                    accessToken = response.headers()[HEADER_ACCESS_TOKEN] ?: ""
//                    client = response.headers()[HEADER_CLIENT] ?: ""
//                }
//
//                if (!sessionAuth.isSessionAuthenticated() && user != null) {
//                    onError("Falha ao autenticar usuário")
//                    return@requestLogin
//                }
//
//                AuthenticationDatabase.saveUser(user!!)
//                AuthenticationDatabase.saveSessionAuthentication(sessionAuth)

                onSuccess()
            },
            onError = {
                onError("Falha no login")
            })
    }
}