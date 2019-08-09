package com.example.parrot.modules.authentication.database
import com.example.parrot.modules.authentication.model.SessionAuthentication
import com.example.parrot.core.SessionController
import com.example.parrot.modules.authentication.model.User
import io.realm.Realm

object AuthenticationDatabase {

    fun saveUser(user: User) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(user)
            realm.commitTransaction()
        }

        SessionController.user = user
    }

    fun saveSession(sessionAuth: SessionAuthentication) {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.copyToRealmOrUpdate(sessionAuth)
            realm.commitTransaction()
        }

        SessionController.user = sessionAuth.user
        SessionController.token = sessionAuth.token

    }

    fun getUser(): User? {

        return Realm.getDefaultInstance().use { realm ->

            realm.where(User::class.java)
                .findFirst()?.let { user ->
                    realm.copyFromRealm(user)
                }
        }
    }

    fun clearAppData() {

        Realm.getDefaultInstance().use { realm ->

            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }

        SessionController.user = null
        SessionController.token = ""
    }

    fun getSessionAuthentication(): SessionAuthentication? {

        return Realm.getDefaultInstance().use { realm ->

            realm.where(SessionAuthentication::class.java)
                .findFirst()?.let { sessionAuth ->
                    realm.copyFromRealm(sessionAuth)
                }
        }
    }

}