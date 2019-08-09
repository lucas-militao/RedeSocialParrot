package com.example.parrot.modules.post.activity.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.core.SessionController
import com.example.parrot.modules.authentication.business.AuthenticationBusiness
import com.example.parrot.modules.authentication.network.AuthenticationNetwork
import com.example.parrot.modules.post.model.PostWrapper
import com.example.parrot.modules.post.adapter.PostAdapter
import com.example.parrot.modules.post.business.PostBusiness
import com.example.parrot.modules.post.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_home_menu.*
import kotlinx.android.synthetic.main.post_holder.*
import org.jetbrains.anko.support.v4.toast

class HomeFragment: Fragment() {

    var listPostWrapper: ArrayList<PostWrapper> = ArrayList()

    lateinit var postViewModel: PostViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)

        setupView()
        subscribeUI()

    }

    private fun setupView() {

        timeLineRecyclerView.adapter = PostAdapter(PostBusiness.listPosts())

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

    }

    private fun posts(): List<PostWrapper> {

        return listOf(
                PostWrapper("I hate Fortaleza's weather!")
        )

    }

}

