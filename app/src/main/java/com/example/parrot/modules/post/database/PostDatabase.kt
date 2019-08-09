package com.example.parrot.modules.post.database

import com.example.parrot.modules.post.model.Post
import io.realm.Realm

object PostDatabase {

    fun doPost(post: Post) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(post)
            realm.commitTransaction()

        }

    }

    fun getPosts(): MutableList<Post>? {

        return Realm.getDefaultInstance().use {realm ->

            realm.where(Post::class.java)
                    .findAll()?.let { posts ->
                        realm.copyFromRealm( posts
                        )
                    }

        }
    }

}