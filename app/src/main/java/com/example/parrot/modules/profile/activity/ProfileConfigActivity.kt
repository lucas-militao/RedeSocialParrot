package com.example.parrot.modules.profile.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.PrincipalActivity
import com.example.parrot.R
import com.example.parrot.core.activity.BaseActivity
import com.example.parrot.modules.authentication.viewmodel.AuthenticationViewModel
import kotlinx.android.synthetic.main.activity_config_user.*
import kotlinx.coroutines.awaitAll
import org.jetbrains.anko.startActivity

class ProfileConfigActivity : BaseActivity() {

    lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_user)

        authenticationViewModel = ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)



        setupView()
    }

    fun setupView() {

        setSupportActionBar(toolbar)

        supportActionBar?.run {
            title = "Editar Perfil"
            setDisplayHomeAsUpEnabled(true)
        }

        saveButton.setOnClickListener {

            authenticationViewModel.updateUser(
                    nameField.text.toString(),
                    usernameField.text.toString(),
                    passwordField.text.toString(),
                    emailField.text.toString(),
                    foto = ""
           )
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item?.itemId == android.R.id.home) {
            startActivity<PrincipalActivity>()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}