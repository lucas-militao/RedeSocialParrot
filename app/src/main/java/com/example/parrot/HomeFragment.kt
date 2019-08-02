package com.example.parrot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home_menu.*
import kotlinx.android.synthetic.main.fragment_home_menu.timeLineRecyclerView
import kotlinx.android.synthetic.main.fragment_home_menu.view.*

class HomeFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupView()

    }

    fun setupView() {

        timeLineRecyclerView.adapter = PostAdapter(posts())

    }

    private fun posts(): List<Post> {

        return listOf(
                Post("I hate Fortaleza's weather!")
        )

    }

}

