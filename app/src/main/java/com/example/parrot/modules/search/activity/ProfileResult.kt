package com.example.parrot.modules.search.activity

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.core.activity.BaseActivity
import com.example.parrot.inTransaction
import com.example.parrot.modules.post.viewmodel.PostViewModel
import com.example.parrot.modules.profile.activity.ProfileFragment
import com.example.parrot.modules.search.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_profile_menu.*

class ProfileResult : BaseActivity() {

    lateinit var profileViewModel: ProfileViewModel
    lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_result)

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)


        supportFragmentManager.inTransaction {

            replace(R.id.fragmentContainer, ProfileFragment().apply {
                arguments = intent.extras
            })

        }
    }

}