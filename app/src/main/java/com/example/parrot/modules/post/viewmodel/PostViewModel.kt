package com.example.parrot.modules.post.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.core.network.BaseNetwork.RequestStatus.FINISHED
import com.example.parrot.core.network.BaseNetwork.RequestStatus.STARTED
import com.example.parrot.core.viewmodel.BaseViewModel
import com.example.parrot.livedata.SingleLiveEvent
import com.example.parrot.modules.post.business.PostBusiness
import com.example.parrot.modules.post.business.PostBusiness.doPost
import com.example.parrot.modules.post.database.PostDatabase
import com.example.parrot.modules.post.model.Post

class PostViewModel : BaseViewModel() {

    var foto: String = ""
    var mensagem: String = ""
    private val _post = MutableLiveData<List<Post>>()
    val post: LiveData<List<Post>> = _post


    var onPostSucessful = SingleLiveEvent<Void>()
    val onPostRequestStatus = MutableLiveData<BaseNetwork.RequestStatus>()

    fun checkData(mensagem: String?) {

        if (!PostBusiness.isTextPostValid(mensagem)) {
            onError.value = "É necessário digitar alguma coisa!"
            return
        }

    }

    fun doPost() {

        onPostRequestStatus.value = STARTED

        doPost(mensagem, foto,
                onSuccess = {
                    _post.value = PostBusiness.getPostsDB()
                    onPostSucessful.call()
                    onPostRequestStatus.value = FINISHED
                },
                onError = {
                    onError.value = it
                    onPostRequestStatus.value = FINISHED
                })

    }

    fun deletePost(post: Post) {

        onPostRequestStatus.value = STARTED

        PostBusiness.deletePost(post,
            onSuccess = {
                _post.value = PostDatabase.getPosts()
                onPostSucessful.call()
                onPostRequestStatus.value = FINISHED
            },
            onError = {
                onError.value = it
                onPostRequestStatus.value = FINISHED
            })

    }

    fun getPosts() {
        PostBusiness.getPosts(
            onSuccess = {
                _post.value = it
            },
            onError = {
                print("Erro")
            }
        )
    }

    fun curtir(post: Post) {

        onPostRequestStatus.value = STARTED

        PostBusiness.curtir(post,
            onSuccess = {
                _post.value = PostDatabase.getPosts()
                onPostSucessful.call()
                onPostRequestStatus.value = FINISHED
            },
            onError = {
                onError.value = it
                onPostRequestStatus.value = FINISHED
            })
    }

}