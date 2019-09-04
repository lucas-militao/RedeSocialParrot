package com.example.parrot.modules.notification.business

import com.example.parrot.core.SessionController
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.notification.database.NotificationDatabase
import com.example.parrot.modules.notification.model.Notification
import com.example.parrot.modules.notification.network.NotificationNetwork
import com.example.parrot.modules.search.database.ProfileDatabase

object NotificationBusiness {


    fun getNotifications(
            onSuccess: (list: MutableList<User>) -> Unit,
            onError: (message: String) -> Unit
    ) {

        NotificationNetwork.requestInvitationList(
                onSuccess = {
                    var notification : Notification = Notification()
                    notification.id = SessionController.user!!.id
                    NotificationDatabase.saveNotifications(notification)
                    onSuccess(it)
                },
                onError = {
                    onError("Erro")
                }
        )

    }

    fun acceptInvitation(
            id: Int,
            onSuccess: (friend: User) -> Unit,
            onError: (message: String) -> Unit
    ) {

        NotificationNetwork.acceptInvitation(id,
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError("Erro")
                })

    }

    fun getNotificationDB(id: Int) : Notification? {
        return NotificationDatabase.getNotification(id)
    }
}