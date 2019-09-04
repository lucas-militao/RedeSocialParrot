package com.example.parrot.modules.notification.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.modules.notification.adapter.NotificationAdapter
import com.example.parrot.modules.notification.viewmodel.NotificationViewModel
import kotlinx.android.synthetic.main.fragment_notification_menu.*

class NotificationFragment: Fragment() {

    lateinit var notificationViewModel: NotificationViewModel
    lateinit var notificationAdapter: NotificationAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_notification_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        notificationViewModel = ViewModelProviders.of(this).get(NotificationViewModel::class.java)

        setupView()
        subscribeUI()

        solicitacoes.adapter = notificationAdapter
    }

    fun setupView() {

        notificationAdapter = NotificationAdapter(
                {
                    notificationViewModel.acceptInvitation(it)
                }
        )
        notificationViewModel.getNotifications()


    }

    fun subscribeUI() {

        with(notificationViewModel) {

            notifications.observe(this@NotificationFragment, Observer {
                notificationAdapter.updateNotificationList(it)
            })

        }

    }

}