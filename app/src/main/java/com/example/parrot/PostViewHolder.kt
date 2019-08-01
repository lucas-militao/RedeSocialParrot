package com.example.parrot

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_holder.view.*

class PostViewHolder(view:View): RecyclerView.ViewHolder(view) {

    companion object {

        fun build(parent: ViewGroup) : PostViewHolder{
            return PostViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.post_holder, parent, false))
        }

    }

    fun bind(post: Post?) {

        with(itemView) {

            post?.imgIconProfile?.let { imgProfileIconPost.setImageResource(it) }
            post?.imgPost?.let { imgPost.setImageResource(it) }
            txtProfileNick.text = post?.nickName
            txtHowDoYouFeel.text = post?.textPost

        }

    }

}