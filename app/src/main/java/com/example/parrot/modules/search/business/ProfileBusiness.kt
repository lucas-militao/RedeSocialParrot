package com.example.parrot.modules.search.business

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.search.database.ProfileDatabase
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
                      onSuccess: (results: MutableList<User>) -> Unit,
                      onError: (message: String) -> Unit) {

        ProfileNetwork.requestProfile(search,
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError("Usuário não encontrado!")
                })
    }

    fun saveProfile(profile: User) {

        ProfileDatabase.saveProfile(profile)

    }

    fun getProfile(profileID: Int,
                   onSuccess: (user: User?) -> Unit,
                   onError: (message: String) -> Unit) {

        ProfileNetwork.requestProfile(profileID,
                onSuccess = {
                    onSuccess(ProfileDatabase.getProfile2(profileID))
                },
                onError = {
                    onError("Erro")
                })

    }

    fun getProfileFromDB(id: Int): User? = ProfileDatabase.getProfile2(id)

}