package com.example.parrot.modules.post.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Post : RealmObject() {

    @PrimaryKey
    var id = -1

    var mensagem = ""

}