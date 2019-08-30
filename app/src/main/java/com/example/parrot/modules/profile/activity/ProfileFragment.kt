package com.example.parrot.modules.profile.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.post.adapter.PostAdapter
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.viewmodel.PostViewModel
import com.example.parrot.modules.search.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile_menu.*

class ProfileFragment: Fragment() {

    lateinit var profileViewModel: ProfileViewModel
    lateinit var postViewModel: PostViewModel

    lateinit var postAdapter: PostAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)
        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)

        subscribeUI()
    }

    fun setupProfileInfo(profile: User) {
        postAdapter = PostAdapter(
                {},
                {})

        profilePosts.adapter = postAdapter

        userNick.text = profile.username
        followers.text = profile.amigos?.size.toString() + " amigos"
    }


    fun subscribeUI() {

        with(profileViewModel) {
            profile.observe(this@ProfileFragment, Observer { user ->

                user?.let {
                    setupProfileInfo(it)
                }

            })

            posts.observe(this@ProfileFragment, Observer { posts ->

                posts?.let {
                    postAdapter.updateListPosts(it)
                }

            })
        }

    }

    fun setupView() {

        solicitationButton.setOnClickListener {



        }

    }

}