package com.example.parrot

import android.app.Activity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_photo_cadastro.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register_fields.*
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.toast
import java.lang.reflect.Proxy

class RegisterUserActivity : BaseActivity() {

    lateinit var registerViewModel: RegisterViewModel

    var id_dados_fragment = R.id.fragment_fields_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, RegisterFieldsFragment.newInstance(), "RegistroCampos")
                    .commit()

        }

        setupView()

    }

    override fun onBackPressed() {
        super.onBackPressed()

        when(id_dados_fragment) {

            R.id.fragment_fields_register -> {
                finish()
            }

            R.id.fragment_photo_register -> {
                supportFragmentManager.inTransaction {
                    replace(R.id.fragment_container, RegisterFieldsFragment())
                }

                id_dados_fragment = R.id.fragment_fields_register
            }

        }
    }

    fun changeFragment() {

        supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, RegisterPhotoFragment.newInstance(), "RegistrarFoto")
                .commit()

        id_dados_fragment = R.id.fragment_photo_register

    }


    private fun setupView() {

        setSupportActionBar(toolbar)

        supportActionBar?.run {
            title = getString(R.string.toolbar_title_cadastro)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            finish()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}