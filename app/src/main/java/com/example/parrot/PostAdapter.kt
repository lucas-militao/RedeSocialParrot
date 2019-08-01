package com.example.parrot

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.time.chrono.ChronoLocalDate

class PostAdapter : RecyclerView.Adapter<PostViewHolder>(){

    val listPosts: List<Any> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder.build(parent)
    }

    override fun getItemCount(): Int {

        return listPosts.size

    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {

        val post: Post = listPosts[position] as Post
        holder.bind(post)

    }
}