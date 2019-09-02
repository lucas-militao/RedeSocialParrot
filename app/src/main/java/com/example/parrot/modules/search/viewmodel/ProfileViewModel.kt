package com.example.parrot.modules.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.core.network.BaseNetwork.RequestStatus.FINISHED
import com.example.parrot.core.network.BaseNetwork.RequestStatus.STARTED
import com.example.parrot.core.viewmodel.BaseViewModel
import com.example.parrot.livedata.SingleLiveEvent
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.search.business.ProfileBusiness
import com.example.parrot.modules.search.model.Solicitacao

class ProfileViewModel : BaseViewModel() {

    private val _profiles = MutableLiveData<List<User>>()
    val profiles: LiveData<List<User>> = _profiles

    private val _profile = MutableLiveData<User>()
    val profile: LiveData<User> = _profile

    private val _invitations = MutableLiveData<List<User>>()
    val invitations: LiveData<List<User>> = _invitations

    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = _posts

    var onSearchProfileSucessful = SingleLiveEvent<Void>()
    val onProfileRequestSucessful = MutableLiveData<BaseNetwork.RequestStatus>()

    fun getProfiles() {
        ProfileBusiness.getProfiles(
                onSuccess = {
                    _profiles.value = it
                },
                onError = {
                    onError.value = it
                }
        )
    }

    fun searchProfile(search: String) {

        onProfileRequestSucessful.value = STARTED

        ProfileBusiness.searchProfile(search,
                onSuccess = {
                    _profiles.value = it
                    onSearchProfileSucessful.call()
                    onProfileRequestSucessful.value = FINISHED
                },
                onError = {
                    onError
                    onProfileRequestSucessful.value = FINISHED
                })
    }

    fun saveProfileDB(profile: User) {
        ProfileBusiness.saveProfile(profile)
    }

    fun getProfileFromDB(id: Int) {
        ProfileBusiness.getProfileFromDB(id)
    }

    fun getProfile(profileID: Int) {

        onProfileRequestSucessful.value = STARTED

        ProfileBusiness.getProfile(profileID,
                onSuccess = {
                    _profile.value = it?.usuario
                    _posts.value = it?.postagens
                    onSearchProfileSucessful.call()
                    onProfileRequestSucessful.value = FINISHED
                },
                onError = {
                    print("Erro")
                    onProfileRequestSucessful.value = FINISHED
                })
    }

    fun solicitation(id: Int) {
        ProfileBusiness.solicitation(id)
    }

    fun getSolicitationList() {
        ProfileBusiness.listaSolicitacao(
                onSuccess = {
                },
                onError = {
                    print(it)
                }
        )
    }

    fun getInvitationList() {
        ProfileBusiness.listaConvites(
                onSuccess = {
                    _invitations.value = it
                },
                onError = {
                    print(it)
                }
        )
    }

    fun getSolicitacaoDB(id: Int) : Solicitacao {
        return getSolicitacaoDB(id)
    }

}