package com.example.parrot

import com.google.gson.annotations.SerializedName

class UserWrapper(var email: String, var password: String) {

    constructor(): this("", "")

    var nome: String = ""

    var username: String = ""

    var foto: String = ""

    @SerializedName("password_confirmation")
    var passwordConfirmation: String = ""

}