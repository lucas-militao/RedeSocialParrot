package com.example.parrot.modules.authentication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SessionAuthentication : RealmObject() {

    @PrimaryKey
    var identifier: Int = 0

    var uid: String = ""
    var client: String = ""
    var accessToken: String = ""

    fun isSessionAuthenticated(): Boolean {
        return uid.isNotEmpty() && client.isNotEmpty() && accessToken.isNotEmpty()
    }
}