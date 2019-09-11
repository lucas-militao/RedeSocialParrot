package com.example.parrot

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.core.SessionController
import com.example.parrot.core.activity.BaseActivity
import com.example.parrot.modules.post.activity.fragment.HomeFragment
import com.example.parrot.modules.notification.activity.fragment.NotificationFragment
import com.example.parrot.modules.profile.activity.ProfileFragment
import com.example.parrot.modules.search.activity.fragment.SearchFragment
import com.example.parrot.modules.authentication.business.AuthenticationBusiness
import com.example.parrot.modules.post.viewmodel.PostViewModel
import com.example.parrot.modules.profile.activity.ProfileConfigActivity
import kotlinx.android.synthetic.main.activity_principal.*
import kotlinx.android.synthetic.main.fragment_profile_menu.*
import org.jetbrains.anko.startActivity

class PrincipalActivity: BaseActivity() {

    lateinit var postViewModel: PostViewModel

    companion object {

        const val CURRENT_BAT_SELECTED_ITEM = "selecteditem"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        postViewModel = ViewModelProviders.of(this).get(PostViewModel::class.java)

        setupActivity()

        bottom_menu.setOnNavigationItemSelectedListener {menu ->

            return@setOnNavigationItemSelectedListener when(menu.itemId) {

                R.id.menu_home -> {

                    supportFragmentManager.inTransaction {

                        toolbar.title = "Parrot"
                        replace(R.id.fragmentContainer, HomeFragment())

                    }
                    true

                }

                R.id.menu_search -> {

                    supportFragmentManager.inTransaction {

                        toolbar.title = "Busca"
                        replace(R.id.fragmentContainer, SearchFragment())

                    }
                    true

                }

                R.id.menu_notification -> {

                    supportFragmentManager.inTransaction {

                        toolbar.title = "Notificações"
                        replace(R.id.fragmentContainer, NotificationFragment())

                    }
                    true

                }

                R.id.menu_profile -> {

                    supportFragmentManager.inTransaction {

                        toolbar.title = "Perfil"
                        replace(R.id.fragmentContainer, ProfileFragment().apply {

                            val bundle = Bundle().apply {
                                SessionController.user?.id?.let { putInt("userID", it) }
                            }
                            arguments = bundle
                        })

                    }
                    true

                }

                else -> false
            }
        }

        savedInstanceState?.let {
            bottom_menu.selectedItemId = it.getInt(CURRENT_BAT_SELECTED_ITEM)
        } ?: run { bottom_menu.selectedItemId = R.id.menu_home }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_BAT_SELECTED_ITEM, bottom_menu.selectedItemId)
    }

    override fun onDestroy() {
        super.onDestroy()
        print("AAAAAAAAAAAAAA")
        AuthenticationBusiness.doLogout()
    }

    fun setupActivity() {
        setSupportActionBar(toolbar)
    }

    private fun configProfile() {
        startActivity<ProfileConfigActivity>()
    }

}