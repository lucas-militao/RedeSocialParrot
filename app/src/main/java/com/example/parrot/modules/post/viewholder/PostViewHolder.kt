package com.example.parrot.modules.post.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.model.PostWrapper
import kotlinx.android.synthetic.main.post_holder.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(post: Post, onCurtir:(post: Post) -> Unit) {

        itemView.txtPost.text = post.mensagem
        itemView.txtLikeCount.text = post.curtidas.toString()
        if (post.curtido) {

        }

        itemView.imgCurtir.setOnClickListener {
            onCurtir(post)
        }

    }

}