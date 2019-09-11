package com.example.parrot.modules.authentication.network

import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.authentication.model.UserWrapper
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*

interface AuthenticationAPI {

    @POST("usuario/login")
    fun requestLogin(
            @Body user: UserWrapper
    ): Call<User>

    @POST("usuario")
    fun requestRegisterUser(
            @Body user: UserWrapper
    ): Observable<Any>

    @PUT("/usuario")
    fun requestUpdateUser(
            @Body user: UserWrapper,
            @Header(BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Call<User>


}