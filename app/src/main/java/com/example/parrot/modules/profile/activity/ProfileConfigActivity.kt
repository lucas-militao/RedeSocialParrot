package com.example.parrot.modules.profile.activity

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.PrincipalActivity
import com.example.parrot.R
import com.example.parrot.core.activity.BaseActivity
import com.example.parrot.modules.authentication.business.AuthenticationBusiness
import com.example.parrot.modules.authentication.viewmodel.AuthenticationViewModel
import com.example.parrot.util.imageupload.ImageLoader
import kotlinx.android.synthetic.main.activity_config_user.*
import kotlinx.android.synthetic.main.fragment_register_photo.*
import kotlinx.coroutines.awaitAll
import org.jetbrains.anko.startActivity

class ProfileConfigActivity : BaseActivity() {

    val GALLERY_REQUEST_CODE = 1

    var intent = null

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

        profileImageView.setOnClickListener {

            ImageLoader.pickFromGallery()

        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item?.itemId == android.R.id.home) {
            startActivity<PrincipalActivity>()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun pickFromGallery() {

        val intent: Intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"

        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)

        startActivityForResult(intent, GALLERY_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        var bitmapImage = ImageLoader.loadImage(resultCode, resultCode, data, this)

        if(resultCode == Activity.RESULT_OK) {

            when(requestCode) {

                GALLERY_REQUEST_CODE -> {

                }

            }

        }

    }

}