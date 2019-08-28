package com.example.parrot.modules.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.R
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.search.viewholder.ProfileViewHolder

class ProfileAdapter(var clickProfile: (profile: User) -> Unit) : RecyclerView.Adapter<ProfileViewHolder>(){

    private var users: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_holder, parent, false)
        return ProfileViewHolder(view)
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bindView(users[position], clickProfile)
    }

    fun updateProfileList(list: List<User>) {
        users = list
        notifyDataSetChanged()
    }

}