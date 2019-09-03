package com.example.parrot.modules.notification.business

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.notification.network.NotificationNetwork

object NotificationBusiness {


    fun getNotifications(
            onSuccess: (list: List<User>) -> Unit,
            onError: (message: String) -> Unit
    ) {

        NotificationNetwork.requestInvitationList(
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError("Erro")
                }
        )

    }
}