package com.example.parrot.modules.search.database

import com.example.parrot.modules.authentication.model.User
import io.realm.Case
import io.realm.Realm
import io.realm.Sort

object ProfileDatabase {

    fun getProfiles(): MutableList<User>? {

        return Realm.getDefaultInstance().use { realm ->

            realm.where(User::class.java)
                    .sort("nome", Sort.ASCENDING)
                    .findAll()?.let { users ->
                        realm.copyFromRealm( users )
                    }
        }
    }

    fun saveProfiles(profiles: List<User>) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.delete(User::class.java)
            realm.copyToRealmOrUpdate(profiles)
            realm.commitTransaction()

        }

    }

//    fun searchProfile(username: String) : List<User>? {
//
//        return Realm.getDefaultInstance().use { realm ->
//
//            realm.where(User::class.java)
//                    .like(User::username.name, username)
//                    .sort("username", Sort.ASCENDING)
//                    .findAll()?.let { profiles ->
//                        realm.copyFromRealm(profiles)
//                    }
//
//        }
//
//    }

//    fun searchProfile(search: String) : List<User>? {
//
//        return Realm.getDefaultInstance().use {  realm ->
//            realm.where(User::class.java)
//                    .contains("username", search, Case.INSENSITIVE)
//                    .findAll()?.let {  results ->
//                        realm.copyFromRealm(results)
//                    }
//        }
//
//    }
}