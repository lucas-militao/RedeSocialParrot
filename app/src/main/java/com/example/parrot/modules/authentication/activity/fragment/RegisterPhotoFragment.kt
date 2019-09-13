package com.example.parrot.modules.authentication.activity.fragment

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.parrot.R
import com.example.parrot.modules.authentication.viewmodel.RegisterViewModel
import kotlinx.android.synthetic.main.fragment_register_photo.*
import org.jetbrains.anko.support.v4.toast
import android.database.Cursor
import android.graphics.BitmapFactory
import com.example.parrot.modules.authentication.business.AuthenticationBusiness

@Suppress("DEPRECATION")
class RegisterPhotoFragment : Fragment() {

    lateinit var registerViewModel: RegisterViewModel
    private val GALLERY_REQUEST_CODE = 1

    companion object {

        fun newInstance(): RegisterPhotoFragment {
            return RegisterPhotoFragment()
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        registerViewModel = ViewModelProviders.of(activity!!).get(RegisterViewModel::class.java)

        setupActivity()
        subscribeUI()
    }


    private fun setupActivity() {

        profileImage.setOnClickListener {
            if(ContextCompat.checkSelfPermission(activity!!,
                            Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!, Manifest.permission.READ_EXTERNAL_STORAGE)) {

                    ActivityCompat.requestPermissions(activity!!,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                            GALLERY_REQUEST_CODE)

                }
                else {
                    ActivityCompat.requestPermissions(activity!!,
                        arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                        GALLERY_REQUEST_CODE)
                }
            } else {
                pickFromGallery()
            }


        }

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

    private fun pickFromGallery() {

        val intent: Intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"

        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)

        startActivityForResult(intent, GALLERY_REQUEST_CODE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val selectedImage : Uri

        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {
                GALLERY_REQUEST_CODE -> {
                    selectedImage = data!!.data!!

                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    val cursor: Cursor = context!!.contentResolver.query(selectedImage,
                            filePathColumn,
                            null, null, null)!!

                    cursor.moveToFirst()

                    var columnIndex = cursor.getColumnIndex(filePathColumn[0])

                    var imgDecodableString = cursor.getString(columnIndex)

                    cursor.close()

                    profileImage.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString))

                    registerViewModel.foto = imgDecodableString
                    AuthenticationBusiness.saveImage(imgDecodableString.toString())

                }
            }
        }
    }
}

