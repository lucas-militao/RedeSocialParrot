package com.example.parrot.modules.search.network

import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User

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
            onSuccess: (result: User) -> Unit,
            onError: () -> Unit
    ) {
        doRequest(API, onSuccess, onError) { requestProfile(id) }
    }

}