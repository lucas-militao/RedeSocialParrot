package com.example.parrot.modules.search.network

import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.search.model.Profile
import com.example.parrot.modules.search.model.Solicitacao

object ProfileNetwork : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().create(ProfileAPI::class.java) }

    fun requestProfiles(
            onSuccess: (users: MutableList<User>) -> Unit,
            onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError) { requestProfiles() }
    }

    fun requestProfile(
            search: String,
            onSuccess: (results: MutableList<User>) -> Unit,
            onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError) { requestProfile(search) }
    }

    fun requestProfile(
            id: Int,
            onSuccess: (profile: Profile) -> Unit,
            onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError) { requestProfile(id) }
    }

    fun requestFriend(
            id: Int,
            onSuccess: (solicitacao: Solicitacao) -> Unit,
            onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError) { requestFriend(id) }
    }

    fun requestSolicitationList(
            onSuccess: (results: MutableList<User>) -> Unit,
            onError: () -> Unit
    ){
        doRequest(API, onSuccess, onError) { requestSolicitationList() }
    }

    fun requestInvitationList(
            onSuccess: (results: MutableList<User>) -> Unit,
            onError: () -> Unit
    ){
        doRequest(API, onSuccess, onError) { requestInvitationList() }
    }

}