package com.example.parrot

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.post_holder.view.*
import java.util.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(post: Post) {

        itemView.txtHowDoYouFeel.text = post.textPost

    }

}