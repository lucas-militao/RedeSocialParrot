package com.example.parrot.modules.authentication.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SessionAuthentication : RealmObject() {

    @PrimaryKey
    var id = -1

    var user : User? = null
    var token: String = ""

}