package com.example.parrot.modules.notification.model

import com.example.parrot.modules.authentication.model.User
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Notification : RealmObject() {

    @PrimaryKey
    var id = -1

    var solicitacoes: RealmList<User>? = null

}