package com.example.parrot.modules.authentication.viewmodel

import com.example.parrot.core.SessionController
import com.example.parrot.livedata.SingleLiveEvent
import com.example.parrot.core.viewmodel.BaseViewModel
import com.example.parrot.modules.authentication.business.AuthenticationBusiness
import com.example.parrot.modules.authentication.database.AuthenticationDatabase

class AuthenticationViewModel : BaseViewModel() {

    val onLoginSuccessful = SingleLiveEvent<Void>()

    fun checkUser() {

        if (AuthenticationBusiness.hasUserLogged()) {

            onLoginSuccessful.call()

            AuthenticationDatabase.getSessionAuthentication()?.apply {

                SessionController.user = this.user
                SessionController.token = this.token

            }
        }
    }

    fun login(email: String?, password: String?) {

        if (!AuthenticationBusiness.isEmailValid(email)) {
            onError.value = "Email inválido"
            return
        }

        if (!AuthenticationBusiness.isPasswordValid(password)) {
            onError.value = "Senha inválida"
            return
        }

        AuthenticationBusiness.doLogin(email!!, password!!,
                onSuccess = {
                    onLoginSuccessful.call()
                },
                onError = {
                    onError.value = it
                })
    }

    fun updateUser(
            nome: String,
            username: String,
            senha: String,
            email: String,
            foto: String
    ) {

        AuthenticationBusiness.doUpdate(nome, email, username, senha, foto)

    }
}