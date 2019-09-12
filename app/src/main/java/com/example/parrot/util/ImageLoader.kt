package com.example.parrot.util

import android.content.Intent
import androidx.core.app.ActivityCompat.startActivityForResult

object ImageLoader {

    const val GALLERY_REQUEST_CODE = 1

    private fun pickFromGallery() : Intent {
        val intent: Intent = Intent(Intent.ACTION_PICK)

        intent.type = "image/*"

        val mimeTypes = arrayOf("image/jpeg", "image/png")
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)

        return intent
    }

    private fun loadImage(requestCode: Int, resultCode: Int) {



    }
}