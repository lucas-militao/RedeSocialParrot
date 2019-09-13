package com.example.parrot.util.imageupload

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import com.example.parrot.core.network.BaseNetwork

object ImageLoader {

    const val GALLERY_REQUEST_CODE = 1

    fun pickFromGallery() {
        val intent: Intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"

        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)

    }

    fun loadImage(requestCode: Int, resultCode: Int, data: Intent?, context : Context) {

        val selectedImage : Uri

        if (resultCode == Activity.RESULT_OK) {
            when(requestCode) {

                GALLERY_REQUEST_CODE -> {

                    selectedImage = data!!.data

                    val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)

                    val cursor = context.contentResolver.query(selectedImage,
                        filePathColumn, null, null, null)

                    cursor.moveToFirst()

                    var columnIndex = cursor.getColumnIndex(filePathColumn[0])

                    var imgDecodableString = cursor.getString(columnIndex)

                    cursor.close()
                }
            }
        }
    }

}