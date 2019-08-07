package com.example.parrot.modules.authentication.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.core.activity.BaseActivity
import com.example.parrot.inTransaction
import com.example.parrot.modules.authentication.activity.fragment.RegisterFieldsFragment
import com.example.parrot.modules.authentication.activity.fragment.RegisterPhotoFragment
import com.example.parrot.modules.authentication.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterUserActivity : BaseActivity() {

    lateinit var registerViewModel: RegisterViewModel

    var id_dados_fragment = R.id.fragment_fields_register

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)

        if (savedInstanceState == null) {

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragmentContainer, RegisterFieldsFragment.newInstance(), "RegistroCampos")
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
                    replace(R.id.fragmentContainer, RegisterFieldsFragment())
                }

                id_dados_fragment = R.id.fragment_fields_register
            }

        }
    }

    fun changeFragment() {

        supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, RegisterPhotoFragment.newInstance(), "RegistrarFoto")
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