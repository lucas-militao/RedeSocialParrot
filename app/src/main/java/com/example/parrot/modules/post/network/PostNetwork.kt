package com.example.parrot.modules.post.network

import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.model.PostWrapper
import retrofit2.Call
import retrofit2.Response

object PostNetwork : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().create(PostAPI::class.java) }

    fun doPost(
            mensagem : String,
            onSuccess: (response: Post) -> Unit,
            onError: () -> Unit) {

        val postWrapper = PostWrapper(mensagem)

        API.doPost(postWrapper).enqueue(object : retrofit2.Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {

                onError()
                t.printStackTrace()

            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {

                if (response.isSuccessful) {
                    response.body()?.let { onSuccess(it) }
                } else {
                    onError()
                }

            }
        })
    }

    fun getPosts(
        onSuccess: (posts: MutableList<Post>) -> Unit,
        onError: () -> Unit
    ) {

        doRequest(API, onSuccess, onError) { getPosts() }

    }

}