package com.example.parrot.modules.search.business

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.search.network.ProfileNetwork

object ProfileBusiness {

    fun getProfiles(
            onSuccess: (users: List<User>) -> Unit,
            onError: (message: String) -> Unit
    ) {

        ProfileNetwork.requestProfiles(
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError("Não foi possível atualizar a lista de perfis de usuários!")
                }
        )
    }

    fun searchProfile(search: String,
                      onSuccess: (results: List<User>) -> Unit,
                      onError: (message: String) -> Unit) {

        ProfileNetwork.requestProfile(search,
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError("Usuário não encontrado!")
                })
    }

//    fun searchProfile(username: String,
//                      onSuccess: (users: MutableList<User>) -> Unit,
//                      onError: (message: String) -> Unit)
//            : List<User>? {
//
//        return ProfileDatabase.searchProfile(username) ?: listOf()
//    }



}