package com.example.parrot.modules.post.model

import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import com.google.gson.annotations.SerializedName
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.time.LocalDateTime

open class Post : RealmObject() {

    @PrimaryKey
    var id = -1

    var mensagem = ""

    @SerializedName("criado_em")
    var criadoEm = 0L

    var imagem = ""
    get() = BaseNetwork.BASE_URL + field
    var autor: User? = null
    var curtidas = 0
    var curtido = false

}