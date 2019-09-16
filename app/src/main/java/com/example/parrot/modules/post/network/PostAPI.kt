package com.example.parrot.modules.post.network

import android.database.Observable
import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.post.model.PostWrapper
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*
import com.example.parrot.modules.post.model.Post as Post

interface PostAPI {

    fun list()

    @Multipart
    @POST("/upload")
    fun uploadImage(
        @Part file: MultipartBody.Part,
        @Part("name") responseBody: RequestBody
    ) : Call<ResponseBody>

    @Multipart
    @POST("postagem")
    fun doPost(
        @Part("mensagem") mensagem: String,
        @Part file: MultipartBody.Part,
        @Header(BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): io.reactivex.Observable<Post>

    @GET("postagem?pagina=1")
    fun getPosts(
        @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): io.reactivex.Observable<MutableList<Post>>

    @POST("/curtir/{id}")
    fun curtir (@Path("id") id: Int,
                @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token)
            : io.reactivex.Observable<Post>

    @DELETE("/postagem/{id}")
    fun deletePost (@Path("id") id: Int,
                    @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token)
            : io.reactivex.Observable<Post>

}