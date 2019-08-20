package com.example.parrot.modules.search.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parrot.core.viewmodel.BaseViewModel
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.search.business.ProfileBusiness

class ProfileViewModel : BaseViewModel() {

    private val _profiles = MutableLiveData<List<User>>()
    val profiles: LiveData<List<User>> = _profiles

    fun getProfiles() {

        ProfileBusiness.getUsers(
                onSuccess = {
                    _profiles.value = it
                },
                onError = {
                    onError.value = it
                }
        )

    }

}