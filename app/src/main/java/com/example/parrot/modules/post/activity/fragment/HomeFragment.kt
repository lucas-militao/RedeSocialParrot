package com.example.parrot.modules.post.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.parrot.R
import com.example.parrot.modules.post.adapter.PostAdapter
import com.example.parrot.modules.post.database.PostDatabase
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_home_menu.*
import kotlinx.android.synthetic.main.post_holder.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

const val EXTRA_POST_VIEW_TYPE = "viewType"
const val ESTRA_CONTACT_ID = "contactID"

class HomeFragment: Fragment() {

    lateinit var postViewModel: PostViewModel

    lateinit var postAdapter: PostAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_menu, container, false)


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)

        setupView()
        subscribeUI()

        postViewModel.getPosts()
    }

    private fun setupView() {

        postAdapter = PostAdapter {
            postViewModel.curtir(it)
        }

        timeLineRecyclerView.adapter = postAdapter

        buttonPost.setOnClickListener {
            postViewModel.doPost(postField.text.toString())
        }

    }

    private fun subscribeUI() {

        postViewModel.onError.observe(this, Observer {
            toast(it)
        })

        postViewModel.onPostSucessful.observe(this, Observer {
            toast("post realizado com sucesso")
        })

        postViewModel.post.observe(this, Observer {
            postAdapter?.updateListPosts(it)
        })

    }
}

