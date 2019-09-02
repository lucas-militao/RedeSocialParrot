package com.example.parrot.modules.notification.network

import com.example.parrot.core.network.BaseNetwork

object NotificationNetwork : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().create(NotificationNetwork::class.java) }

    fun requestInvitationList () {

    }

}