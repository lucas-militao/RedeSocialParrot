package com.example.parrot.modules.search.database

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.search.model.Profile
import com.example.parrot.modules.search.model.Solicitacao
import io.realm.Case
import io.realm.Realm
import io.realm.Sort
import io.realm.kotlin.where

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

    fun getProfile(id: Int): Profile? {
        val realm = Realm.getDefaultInstance()

        realm.where(Profile::class.java).equalTo(Profile::id.name, id).findFirst()?.let {
            return realm.copyFromRealm(it)
        } ?: kotlin.run {
            return null
        }

    }

    fun saveProfile(profile: User) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(profile)
            realm.commitTransaction()

        }
    }

    fun saveProfile(profile: Profile) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(profile)
            realm.commitTransaction()

        }
    }

    fun saveSolicitation(solicitacao: Solicitacao) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(solicitacao)
            realm.commitTransaction()
        }

    }

    fun getSolicitacao(id: Int) : Solicitacao {

        val realm = Realm.getDefaultInstance()

        return realm.where(Solicitacao::class.java)
                .equalTo(Solicitacao::id.name, id)
                .findFirst().let { solicitacao ->
                    realm.copyFromRealm(solicitacao)!!
                }

    }


}