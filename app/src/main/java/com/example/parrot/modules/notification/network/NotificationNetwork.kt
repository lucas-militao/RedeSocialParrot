package com.example.parrot.modules.notification.network

import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User

object NotificationNetwork : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().create(NotificationAPI::class.java) }

    fun requestInvitationList (
            onSuccess: (list: List<User>) -> Unit,
            onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError) { API.requestInvitations() }
    }

}