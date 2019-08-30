package com.example.parrot.modules.search.network

import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.search.model.Profile
import io.reactivex.Observable
import retrofit2.http.*

interface ProfileAPI {

    @POST("/solicitacoes/{id}")
    fun requestFriend(
            @Path("id") id: Int,
            @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    )

    @GET("/usuario")
    fun requestProfiles(
            @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Observable<MutableList<User>>

    @GET("/usuario/")
    fun requestProfile(
            @Query("busca") busca: String,
            @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Observable<MutableList<User>>

    @GET("/usuario/{id}")
    fun requestProfile(
            @Path("id") id: Int,
            @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Observable<Profile>

}