package com.example.parrot

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseActivity() {

    private lateinit var authenticationViewModel: AuthenticationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        authenticationViewModel =
                ViewModelProviders.of(this).get(AuthenticationViewModel::class.java)

        setupView()
        subscribeUI()
    }

    override fun onResume() {
        super.onResume()
        authenticationViewModel.checkUser()
    }

    fun subscribeUI() {

        authenticationViewModel.onError.observe(this, Observer{
            toast(it)
        })

        authenticationViewModel.onLoginSuccessful.observe(this, Observer {
            toast("Login deu certo")
        })

    }

    private fun setupView() {

        login_button.setOnClickListener {

            authenticationViewModel.login(
                    email_field?.editText?.text?.toString(),
                    password_field?.editText?.text?.toString()
            )

        }

        button_register_user.setOnClickListener {

            startActivity<RegisterUserActivity>()

        }
    }
}
