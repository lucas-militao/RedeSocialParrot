package com.example.parrot.modules.notification.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.parrot.core.SessionController
import com.example.parrot.core.viewmodel.BaseViewModel
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.notification.business.NotificationBusiness
import com.example.parrot.modules.notification.database.NotificationDatabase
import com.example.parrot.modules.notification.model.Notification

class NotificationViewModel : BaseViewModel() {

    private val _notifications = MutableLiveData<MutableList<User>>()
    val notifications: LiveData<MutableList<User>> = _notifications

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

    fun acceptInvitation(id: Int) {

        NotificationBusiness.acceptInvitation(id,
                onSuccess = {
                    getNotifications()
                },
                onError = {
                    print(it)
                })
    }

    fun getNotificationDB(id: Int) {

        _notifications.value = NotificationBusiness.getNotificationDB(id)?.solicitacoes

    }

}