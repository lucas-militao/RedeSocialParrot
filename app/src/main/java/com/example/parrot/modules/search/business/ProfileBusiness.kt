package com.example.parrot.modules.search.business

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.search.database.ProfileDatabase
import com.example.parrot.modules.search.network.ProfileNetwork

object ProfileBusiness {

    fun getUsers(
            onSuccess: (users: List<User>) -> Unit,
            onError: (message: String) -> Unit
    ) {

        ProfileDatabase.getProfiles()?.let(onSuccess)

        ProfileNetwork.requestProfiles(
                onSuccess = {
                    ProfileDatabase.saveProfiles(it)
                    onSuccess(it)
                },
                onError = {
                    onError("Não foi possível atualizar a lista de perfis de usuários!")
                }
        )

    }

}