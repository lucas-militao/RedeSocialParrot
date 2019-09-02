package com.example.parrot.modules.search.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Solicitacao : RealmObject(){

    @PrimaryKey
    var id: Int = 0

    var usuario_id = 0
    var solicitado_id = 0
    var created_at = ""
    var updated_at = ""
}