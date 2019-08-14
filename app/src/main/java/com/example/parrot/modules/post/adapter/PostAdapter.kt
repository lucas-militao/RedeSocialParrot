package com.example.parrot.modules.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.R
import com.example.parrot.modules.post.business.PostBusiness
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.model.PostWrapper
import com.example.parrot.modules.post.viewholder.PostViewHolder
import com.example.parrot.modules.post.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.post_holder.view.*

class PostAdapter(var onCurtir: (post: Post) -> Unit) : RecyclerView.Adapter<PostViewHolder>() {

     private var posts: List<Post> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_holder, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindView(posts[position], onCurtir)
    }

    fun updateListPosts(list: List<Post>) {
        posts = list
        notifyDataSetChanged()
    }
}