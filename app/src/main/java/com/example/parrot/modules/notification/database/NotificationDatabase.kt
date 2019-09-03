package com.example.parrot.modules.notification.database

import com.example.parrot.modules.authentication.model.User
import io.realm.Realm

object NotificationDatabase {

    fun saveNotifications(list: List<User>) {

        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.copyToRealmOrUpdate(list)
            it.commitTransaction()
        }

    }

}