package com.example.parrot

import android.widget.Toast

class AuthenticationViewModel : BaseViewModel() {

    val onLoginSuccessful = SingleLiveEvent<Void>()

    fun checkUser() {

        if (AuthenticationBusiness.hasUserLogged()) {
            onLoginSuccessful.call()
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
}