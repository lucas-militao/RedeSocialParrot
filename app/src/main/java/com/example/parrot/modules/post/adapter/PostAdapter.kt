package com.example.parrot.modules.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.R
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.model.PostWrapper
import com.example.parrot.modules.post.viewholder.PostViewHolder

class PostAdapter(private val posts: List<Post>) : RecyclerView.Adapter<PostViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_holder, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(posts[position])
    }
}