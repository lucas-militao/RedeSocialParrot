package com.example.parrot.modules.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.modules.search.adapter.ProfileAdapter
import com.example.parrot.modules.search.viewmodel.ProfileViewModel
import kotlinx.android.synthetic.main.fragment_search_menu.*

class SearchFragment: Fragment() {

    lateinit var profileViewModel: ProfileViewModel

    lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        profileViewModel = ViewModelProviders.of(activity!!).get(ProfileViewModel::class.java)

        setupView()
        subscribeUI()

        profileViewModel.getProfiles()
    }

    private fun setupView() {

        profileAdapter = ProfileAdapter()

        profileList.adapter = profileAdapter

    }

    private fun subscribeUI() {

        profileViewModel.profiles.observe(this, Observer {
            profileAdapter?.updateProfileList(it)
        })

    }

}