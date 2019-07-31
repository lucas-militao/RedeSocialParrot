package com.example.parrot

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User: RealmObject() {

    @PrimaryKey
    var id: Int = -1

    var uid: String = ""
    var email: String = ""
}