package com.example.parrot.modules.search.business

import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.search.database.ProfileDatabase
import com.example.parrot.modules.search.model.Profile
import com.example.parrot.modules.search.model.Solicitacao
import com.example.parrot.modules.search.network.ProfileNetwork

object ProfileBusiness {

    fun getProfiles(
            onSuccess: (profiles: List<User>) -> Unit,
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
                   onSuccess: (profile: Profile?) -> Unit,
                   onError: (message: String) -> Unit) {

        ProfileNetwork.requestProfile( profileID,
                onSuccess = {
                    it.id = it.usuario!!.id
                    ProfileDatabase.saveProfile(it)
                    onSuccess(it)
                },
                onError = {
                    onError("Erro")
                })

    }

    fun getProfileFromDB(id: Int): Profile? = ProfileDatabase.getProfile(id)

    fun solicitation(id: Int) {

        ProfileNetwork.requestFriend(id,
                onSuccess = {
                    ProfileDatabase.saveSolicitation(it)
                },
                onError = {
                    print("Erro")
                })

    }

    fun listaSolicitacao(onSuccess: (profiles: List<User>) -> Unit,
                         onError: (message: String) -> Unit) {

        ProfileNetwork.requestSolicitationList(
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError("Erro")
                }
        )
    }

    fun listaConvites(onSuccess: (profiles: List<User>) -> Unit,
                      onError: (message: String) -> Unit) {

        ProfileNetwork.requestInvitationList(
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError("Erro")
                }
        )
    }

}