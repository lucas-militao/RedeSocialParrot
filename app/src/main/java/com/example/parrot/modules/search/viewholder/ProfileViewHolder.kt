package com.example.parrot.modules.search.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.PrincipalActivity
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.search.database.ProfileDatabase
import com.example.parrot.modules.search.model.Profile
import kotlinx.android.synthetic.main.fragment_search_menu.view.*
import kotlinx.android.synthetic.main.profile_holder.view.*

class ProfileViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(profile: User, clickProfile: (profile: User) -> Unit) {
        itemView.profileName.text = profile.username

        itemView.setOnClickListener {
            clickProfile(profile)
        }
    }
}