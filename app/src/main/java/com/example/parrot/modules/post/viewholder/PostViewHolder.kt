package com.example.parrot.modules.post.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.modules.post.model.PostWrapper
import kotlinx.android.synthetic.main.post_holder.view.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(postWrapper: PostWrapper) {

        itemView.txtPost.text = postWrapper.mensagem

    }

}