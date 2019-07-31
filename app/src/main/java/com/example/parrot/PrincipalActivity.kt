package com.example.parrot

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_principal.*

class PrincipalActivity: BaseActivity() {

    companion object {

        const val CURRENT_BAT_SELECTED_ITEM = "selecteditem"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)

        bottom_menu.setOnNavigationItemSelectedListener {menu ->

            return@setOnNavigationItemSelectedListener when(menu.itemId) {

                R.id.menu_home -> {

                    supportFragmentManager.inTransaction {

                        replace(R.id.fragmentContainer, HomeFragment())

                    }
                    true

                }

                R.id.menu_search -> {

                    supportFragmentManager.inTransaction {

                        replace(R.id.fragmentContainer, SearchFragment())

                    }
                    true

                }

                R.id.menu_notification -> {

                    supportFragmentManager.inTransaction {

                        replace(R.id.fragmentContainer, NotificationFragment())

                    }
                    true

                }

                R.id.menu_profile -> {

                    supportFragmentManager.inTransaction {

                        replace(R.id.fragmentContainer, ProfileFragment())

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

}