package com.example.parrot

import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthenticationAPI {

    @POST("usuario/login")
    fun requestLogin(@Body user: UserWrapper): Call<UserResponse>

    @POST("usuario")
    fun requestRegisterUser(@Body user: UserWrapper): Observable<Any>

}