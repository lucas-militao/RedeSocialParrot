package com.example.parrot.modules.post.database

import com.example.parrot.modules.post.model.Post
import io.realm.Realm
import io.realm.Sort

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
                    .sort("criadoEm", Sort.DESCENDING)
                    .findAll()?.let { posts ->
                        realm.copyFromRealm(posts)
                    }

        }

    }

    fun getPost(postID : Int): Post? {

        return Realm.getDefaultInstance().use { realm ->

            realm.where(Post::class.java)
                .equalTo(Post::id.name, postID)
                .findFirst()?.let { post ->
                    realm.copyFromRealm(post)
                }
        }
    }

    fun savePosts(posts: List<Post>) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.delete(Post::class.java)
            realm.copyToRealmOrUpdate(posts)
            realm.commitTransaction()

        }
    }

    fun savePost(post: Post) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(post)
            realm.commitTransaction()

        }

    }

    fun deletePost(post: Post) {
        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.where(Post::class.java)
                .equalTo(Post::id.name, post.id)
                .findFirst()
                ?.deleteFromRealm()
            realm.commitTransaction()

        }
    }

}