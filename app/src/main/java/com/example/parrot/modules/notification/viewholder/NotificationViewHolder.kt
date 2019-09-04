package com.example.parrot.modules.notification.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.modules.authentication.model.User
import kotlinx.android.synthetic.main.notification_holder.view.*

class NotificationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(user: User, acceptOnClick : (id: Int) -> Unit) {

        itemView.userNick.text = user.username

        itemView.confirmButton.setOnClickListener {
            acceptOnClick(user.id)
        }
        
    }

}