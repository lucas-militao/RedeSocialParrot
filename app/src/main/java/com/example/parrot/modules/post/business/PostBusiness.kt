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

    fun getPosts(
        onSuccess: (posts: List<Post>) -> Unit,
        onError: (message: String) -> Unit
    ) {

        PostDatabase.getPosts()?.let(onSuccess)

        PostNetwork.getPosts(
            onSuccess = {
                PostDatabase.savePosts(it)
                onSuccess(it)
            },
            onError = {
                onError("Não foi possível atualizar os contatos!")
            }
        )
    }

    fun getPostsDB() : List<Post> {
        return PostDatabase.getPosts()?: listOf()
    }

    fun getPost(postID: Int): Post? {
        return PostDatabase.getPost(postID)
    }

    fun curtir(post : Post,
               onSuccess: () -> Unit,
               onError: (message: String) -> Unit) {

        PostNetwork.curtir( post,
            onSuccess = {
                PostDatabase.savePost(it)
                onSuccess()
            },
            onError = {
                print("error")
            })
    }

    fun deletePost(post: Post,
                   onSuccess: (post: Post) -> Unit,
                   onError: (message: String) -> Unit) {

        PostNetwork.deletePost( post,
            onSuccess = {
                PostDatabase.deletePost(it)
                onSuccess(post)
            },
            onError = {
                print("Erro ao deletar post!")
            })

    }

}