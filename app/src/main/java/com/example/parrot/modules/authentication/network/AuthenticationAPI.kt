package com.example.parrot.modules.authentication.network

import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.authentication.model.UserWrapper
import io.reactivex.Observable
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface AuthenticationAPI {

    @Multipart
    @POST("/upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("name") responseBody: RequestBody
    ) : Call<ResponseBody>

    @POST("usuario/login")
    fun requestLogin(
            @Body user: UserWrapper
    ): Call<User>

    @Multipart
    @POST("usuario")
    fun requestRegisterUser(
            @Part("nome") name: String,
            @Part("email") email: String,
            @Part("username") username: String,
            @Part("password") senha: String,
            @Part file: MultipartBody.Part
    ): Observable<Any>

    @PUT("/usuario")
    fun requestUpdateUser(
            @Body user: UserWrapper,
            @Header(BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Call<User>


}