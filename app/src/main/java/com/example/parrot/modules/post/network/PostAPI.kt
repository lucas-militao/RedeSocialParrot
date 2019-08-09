package com.example.parrot.modules.post.network

import com.example.parrot.core.SessionController
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.model.PostWrapper
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PostAPI {

    @POST("postagem")
    fun doPost(
        @Body post: PostWrapper,
        @Header(BaseNetwork.TOKEN) accessToken: String? = SessionController.token
    ): Call<Post>

//    @GET("postagem?pagina=1")
//    fun getPosts(@Body )

}