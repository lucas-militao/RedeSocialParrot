package com.example.parrot.modules.authentication.network

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.authentication.model.UserWrapper
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthenticationAPI {

    @POST("usuario/login")
    fun requestLogin(@Body user: UserWrapper): Call<User>

    @POST("usuario")
    fun requestRegisterUser(@Body user: UserWrapper): Observable<Any>

    @POST("/curtir/{id}")
    fun requestCurtida(@Path("id") id: Int): Observable<Any>

}