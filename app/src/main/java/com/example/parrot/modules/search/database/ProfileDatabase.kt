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

    fun getProfile(profileID: Int): User? {

        return Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.where(User::class.java)
                    .equalTo(User::id.name, profileID)
                    .findFirst()?.let { user ->
                        realm.copyFromRealm(user)
                    }

        }

    }

    fun getProfile2(id: Int): User? {
        val realm = Realm.getDefaultInstance()

        realm.where(User::class.java).equalTo(User::id.name, id).findFirst()?.let {
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
}