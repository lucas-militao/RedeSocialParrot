package com.example.parrot.modules.search.activity.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.modules.post.adapter.PostAdapter
import com.example.parrot.modules.search.activity.ProfileResult
import com.example.parrot.modules.search.adapter.ProfileAdapter
import com.example.parrot.modules.search.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_search_menu.*

class SearchFragment: Fragment() {

    lateinit var profileViewModel: ProfileViewModel

    lateinit var profileAdapter: ProfileAdapter

    lateinit var intent: Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)

        intent = Intent(activity!!, ProfileResult::class.java)

        setupView()
        subscribeUI()

        profileViewModel.getProfiles()
    }

    private fun setupView() {

        profileAdapter = ProfileAdapter(
                {
                    intent.putExtra("userID", it.id.toInt())
                    profileViewModel.getProfile(it.id)
                    startActivity(intent)
                }
        )

        profileList.adapter = profileAdapter

        searchField.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(p0: Editable?) {}

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                profileViewModel.searchProfile(p0.toString())
            }

        })

    }

    private fun subscribeUI() {

        profileViewModel.profiles.observe(this, Observer {
            profileAdapter?.updateProfileList(it)
        })

    }

}