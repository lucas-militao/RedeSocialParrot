package com.example.parrot.modules.notification.network

import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface NotificationAPI {

    @GET("/solicitacoes/recebidas")
    fun requestInvitations(
            @Header(BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Observable<MutableList<User>>

}