package com.example.parrot.modules.authentication.activity.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.modules.authentication.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.activity_photo_cadastro.*
import org.jetbrains.anko.support.v4.toast

class RegisterPhotoFragment : Fragment() {

    lateinit var registerViewModel: RegisterViewModel

    companion object {

        fun newInstance(): RegisterPhotoFragment {
            return RegisterPhotoFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_photo_cadastro, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerViewModel = ViewModelProviders.of(activity!!).get(RegisterViewModel::class.java)

        setupActivity()
        subscribeUI()
    }


    private fun setupActivity() {

        buttonCadastro.setOnClickListener {
            registerViewModel.registerUser()
        }

    }

    fun subscribeUI() {

        registerViewModel.onError.observe(this, Observer {
            toast(it)
        })

        registerViewModel.onRegisterUserSuccessful.observe(this, Observer {
            toast("Usuário cadastrado com sucesso!")
        })

    }


}

