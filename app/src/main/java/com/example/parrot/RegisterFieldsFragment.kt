package com.example.parrot

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_register_fields.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.toast

class RegisterFieldsFragment : Fragment() {

    lateinit var registerViewModel: RegisterViewModel

    companion object {

        fun newInstance(): RegisterFieldsFragment {
            return RegisterFieldsFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_register_fields, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        registerViewModel = ViewModelProviders.of(activity!!).get(RegisterViewModel::class.java)

        SetupView()
    }



    private fun SetupView() {

        button_next.setOnClickListener {

            if(registerViewModel.checkData(
                            nome_field.editText?.text.toString(),
                            username_field.editText?.text.toString(),
                            emailField.editText?.text.toString(),
                            passwordField.editText?.text.toString(),
                            password_confirmation_field.editText?.text.toString()
                    )) {

                registerViewModel.nome = nome_field.editText?.text.toString()
                registerViewModel.username = username_field.editText?.text.toString()
                registerViewModel.email =  emailField.editText?.text.toString()
                registerViewModel.password = passwordField.editText?.text.toString()
                registerViewModel.passwordConfirmation = password_confirmation_field.editText?.text.toString()

                (activity as? RegisterUserActivity)?.changeFragment()

            } else {
                toast(registerViewModel.onValidateCadastroError.value.toString())
            }

        }

    }

    private fun subscribeUI() {

        with(registerViewModel) {

            onValidateCadastroError.observe(this@RegisterFieldsFragment, Observer { messageArray ->

                alert {

                    var errorMessage = ""

                    messageArray.forEach {
                        errorMessage += "$it "
                    }

                    title = "Erro"
                    message = errorMessage

                    positiveButton(getString(R.string.dialog_positive_button_voltar)) { }
                }.show()
            })

            //SPLASH LAYOUT DE ESPERA PARA O CADASTRO DE NOVO USUÁRIO!!!
//            onRegisterUserRequestStatus.observe(this@RegisterUserActivity, Observer { status ->
//
//                when(status) {
//                    BaseNetwork.RequestStatus.STARTED -> loading.visibility = View.VISIBLE
//                    else -> loading.visibility = View.GONE
//                }
//            })

            onRegisterUserSuccessful.observe(this@RegisterFieldsFragment, Observer {

                alert {
                    message = "Usuário cadastrado com sucesso!"
                    positiveButton("OK") {
                        //finish()
                    }
                }.show()
            })
        }

    }

}