package com.example.parrot.modules.post.network

import android.database.Observable
import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.post.model.PostWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import java.util.*
import com.example.parrot.modules.post.model.Post as Post

interface PostAPI {

    fun list()

    @POST("postagem")
    fun doPost(
        @Body post: PostWrapper,
        @Header(BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Call<Post>

    @GET("postagem?pagina=1")
    fun getPosts(
        @Header (BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): io.reactivex.Observable<MutableList<Post>>

//    @GET("postagem?pagina=1")
//    fun getPosts(@Body )

}