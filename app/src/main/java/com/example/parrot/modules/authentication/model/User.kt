package com.example.parrot.modules.authentication.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User: RealmObject() {

    @PrimaryKey
    var id: Int = -1

    var nome = ""
    var email = ""
    var username = ""
    var foto = ""
    var amigos : RealmList<User>? = null

}