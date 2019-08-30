package com.example.parrot.modules.search.model

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.post.model.Post
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Profile : RealmObject(){

    @PrimaryKey
    var id = -1

    var usuario: User? = null
    var postagens: RealmList<Post> = RealmList()

}