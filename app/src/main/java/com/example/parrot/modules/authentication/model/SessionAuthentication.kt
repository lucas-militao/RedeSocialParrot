package com.example.parrot.modules.authentication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

object SessionAuthentication {

    var user = User()

    var token: String = ""

    fun isSessionAuthenticated(): Boolean {
        return token.isNullOrEmpty()
    }

}