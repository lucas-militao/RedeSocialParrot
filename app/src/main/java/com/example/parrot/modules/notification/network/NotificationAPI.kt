package com.example.parrot.modules.notification.network

import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface NotificationAPI {

    @GET("/solicitacoes/recebidas")
    fun requestInvitations(
            @Header(BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Observable<MutableList<User>>

    @POST("/solicitacoes/amizade/{id}")
    fun acceptInvitation(@Path("id") id: Int,
            @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ) : Observable<User>

}