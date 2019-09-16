package com.example.parrot.modules.post.network

import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.SessionAuthentication
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.model.PostWrapper
import okhttp3.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

object PostNetwork : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().create(PostAPI::class.java) }

    fun curtir(
        post: Post,
        onSuccess: (response: Post) -> Unit,
        onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError){ curtir(post.id) }
    }

    fun doPost(
            mensagem : String,
            imagem : String,
            onSuccess: (response: Post) -> Unit,
            onError: () -> Unit) {

        var file = File(imagem)

        var fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)

        var part = MultipartBody.Part.createFormData("imagem", file.name, fileRequestBody)

//        API.doPost(mensagem, part).enqueue(object : retrofit2.Callback<Any> {
//            override fun onFailure(call: Call<Any>, t: Throwable) {
//                onError()
//                t.printStackTrace()
//            }
//
//            override fun onResponse(call: Call<Any>, response: Response<Any>) {
//                if (response.isSuccessful) {
//                    response.body()?.let { onSuccess(it) }
//                } else {
//                    onError()
//                }
//            }
//        })

        doRequest(API, onSuccess, onError) { doPost(mensagem, part) }
    }

    fun deletePost(
        post: Post,
        onSuccess: (post: Post) -> Unit,
        onError: () -> Unit
    ) {

        doRequest(API, onSuccess, onError) { deletePost(post.id) }

    }

    fun getPosts(
        onSuccess: (posts: MutableList<Post>) -> Unit,
        onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError) { getPosts() }
    }
}