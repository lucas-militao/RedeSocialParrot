package com.example.parrot.modules.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.core.network.BaseNetwork.RequestStatus.FINISHED
import com.example.parrot.core.network.BaseNetwork.RequestStatus.STARTED
import com.example.parrot.core.viewmodel.BaseViewModel
import com.example.parrot.livedata.SingleLiveEvent
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.search.business.ProfileBusiness

class ProfileViewModel : BaseViewModel() {

    private val _profiles = MutableLiveData<List<User>>()
    val profiles: LiveData<List<User>> = _profiles

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

    fun saveProfile(profile: User) {

        ProfileBusiness.saveProfile(profile)

    }

}