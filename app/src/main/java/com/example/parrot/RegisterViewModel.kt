package com.example.parrot

import android.app.usage.UsageEvents
import androidx.lifecycle.MutableLiveData
import com.example.parrot.BaseNetwork.RequestStatus.*

class RegisterViewModel : BaseViewModel() {

    val onValidateCadastroError = SingleLiveEvent<List<String>>()
    val onRegisterUserSuccessful = SingleLiveEvent<Unit>()
    val onRegisterUserRequestStatus = MutableLiveData<BaseNetwork.RequestStatus>()
    var event = SingleLiveEvent<Unit>()

    var nome: String = ""
    var username: String = ""
    var email: String = ""
    var password: String = ""
    var passwordConfirmation: String = ""

    val errorMessages = mutableListOf<String>()

    fun checkData(nome: String, username: String, email: String, password: String, passwordConfirmation: String): Boolean {

        if (!AuthenticationBusiness.isUsernameValid(username)) {
            errorMessages.add("O username não é válido")
            onValidateCadastroError.value = errorMessages
            return false
        }

        if (!AuthenticationBusiness.isNameValid(nome)) {
            errorMessages.add("O nome não é válido")
            onValidateCadastroError.value = errorMessages
            return false
        }

        if (!AuthenticationBusiness.isEmailValid(email)) {
            errorMessages.add("O email não é válido")
            onValidateCadastroError.value = errorMessages
            return false
        }

        if (!AuthenticationBusiness.isPasswordValid(password)) {
            errorMessages.add("A senha não é válida")
            onValidateCadastroError.value = errorMessages
            return false
        } else {

            if (password != passwordConfirmation) {
                errorMessages.add("As senhas não conferem")
                onValidateCadastroError.value = errorMessages
                return false
            }

        }

        this.username = username
        this.nome = nome
        this.email = email
        this.password = password
        this.passwordConfirmation = passwordConfirmation

        return true

    }

    fun register(name: String?, username: String?, email: String?, password: String?, passwordConfirmation: String?) {
//
//        if (!AuthenticationBusiness.isUsernameValid(username)) {
//            errorMessages.add("O username não é válido")
//        }
//
//        if (!AuthenticationBusiness.isNameValid(name)) {
//            errorMessages.add("O nome não é válido")
//        }
//
//        if (!AuthenticationBusiness.isEmailValid(email)) {
//            errorMessages.add("O email não é válido")
//        }
//
//        if (!AuthenticationBusiness.isPasswordValid(password)) {
//            errorMessages.add("A senha não é válida")
//        } else {
//
//            if (password != passwordConfirmation) {
//                errorMessages.add("As senhas não conferem")
//            }
//        }

//        if (errorMessages.isNotEmpty()) {
//
//            onValidateCadastroError.value = errorMessages
//
//        } else {

//            onRegisterUserRequestStatus.value = BaseNetwork.RequestStatus.STARTED
//
//            AuthenticationBusiness.registerUser(nome!!, username!!, email!!, password!!,
//
//                    onSuccess = {
//                        onRegisterUserSuccessful.call()
//                        onRegisterUserRequestStatus.value = FINISHED
//                    },
//                    onError = {
//                        onError.value = it
//                        onRegisterUserRequestStatus.value = FINISHED
//                    })

//        }

//        }}

    }

    fun registerUser() {

        onRegisterUserRequestStatus.value = BaseNetwork.RequestStatus.STARTED

        AuthenticationBusiness.registerUser(
                this.nome,
                this.username,
                this.email,
                this.password,

                onSuccess = {
                    onRegisterUserSuccessful.call()
                    onRegisterUserRequestStatus.value = FINISHED
                },
                onError = {
                    onError.value = it
                    onRegisterUserRequestStatus.value = FINISHED
                })
    }

}