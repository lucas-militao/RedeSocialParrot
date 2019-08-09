package com.example.parrot.modules.post.business

import com.example.parrot.modules.post.database.PostDatabase
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.network.PostNetwork

object PostBusiness {

    fun isTextPostValid(txtPost: String?): Boolean {
        return !(txtPost.isNullOrEmpty())
    }

    fun doPost(
            mensagem: String,
            onSuccess: () -> Unit,
            onError: (message: String) -> Unit) {

        PostNetwork.doPost(mensagem,
                onSuccess = {
                    PostDatabase.doPost(it)
                    onSuccess()
                },
                onError = {
                    onError("Falha ao fazer a postagem!")
                })

    }

    fun listPosts(): List<Post> {
        return PostBusiness.listPosts()
    }

}