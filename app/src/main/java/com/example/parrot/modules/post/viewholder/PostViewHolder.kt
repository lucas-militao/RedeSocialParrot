package com.example.parrot.modules.post.viewholder

import android.text.format.DateUtils
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.R
import com.example.parrot.R.drawable
import com.example.parrot.modules.post.model.Post
import kotlinx.android.synthetic.main.post_holder.view.*
import java.util.*

class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindView(post: Post, onCurtir:(post: Post) -> Unit, onDelete:(post: Post) -> Unit) {

        var curtido = if(!post.curtido) drawable.ic_curtir_false else drawable.ic_curtir_true

        itemView.txtPost.text = post.mensagem
        itemView.txtLikeCount.text = post.curtidas.toString()
        itemView.imgCurtir.setImageResource(curtido)

        Locale.setDefault(Locale("PT", "br"))
        itemView.txtTimePost.text = DateUtils.getRelativeTimeSpanString(post.criadoEm * 1000).toString()

        itemView.imgCurtir.setOnClickListener {
            onCurtir(post)
            itemView.imgCurtir.setImageResource(R.drawable.ic_curtir_false)
            itemView.txtLikeCount.text = post.curtidas.toString()
        }

        itemView.txtEditPost.setOnClickListener {
            onDelete(post)
        }

    }

}