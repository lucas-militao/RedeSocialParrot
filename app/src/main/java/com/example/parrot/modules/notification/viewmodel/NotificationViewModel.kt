package com.example.parrot.modules.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parrot.core.viewmodel.BaseViewModel
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.notification.business.NotificationBusiness

class NotificationViewModel : BaseViewModel() {

    private val _notifications = MutableLiveData<List<User>>()
    val notifications: LiveData<List<User>> = _notifications

    fun getNotifications() {

        NotificationBusiness.getNotifications(
                onSuccess = {
                    _notifications.value = it
                },
                onError = {
                    print("Erro")
                }
        )

    }

}