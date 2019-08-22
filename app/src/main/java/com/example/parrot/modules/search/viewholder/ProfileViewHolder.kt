package com.example.parrot.modules.search.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.modules.authentication.model.User
import kotlinx.android.synthetic.main.fragment_search_menu.view.*
import kotlinx.android.synthetic.main.profile_holder.view.*

class ProfileViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(user: User) {
        itemView.profileName.text = user.username
    }

}