package com.example.parrot.modules.notification.database

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.notification.model.Notification
import io.realm.Realm

object NotificationDatabase {

    fun saveNotifications(notification: Notification) {

        Realm.getDefaultInstance().use {
            it.beginTransaction()
            it.copyToRealmOrUpdate(notification)
            it.commitTransaction()
        }

    }

    fun getNotification(id: Int) : Notification? {

        return Realm.getDefaultInstance().use { realm ->

            realm.where(Notification::class.java)
                    .equalTo(Notification::id.name, id)
                    .findFirst()?.let { notification ->
                        realm.copyFromRealm(notification)
                    }
        }

    }

}