package com.example.parrot.modules.notification.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.R
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.notification.viewholder.NotificationViewHolder

class NotificationAdapter: RecyclerView.Adapter<NotificationViewHolder>() {

    private var notifications: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_holder, parent, false)
        return NotificationViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
        holder.bindView(notifications[position])
    }

    fun updateNotificationList(list: List<User>) {
        notifications = list
        notifyDataSetChanged()
    }

}