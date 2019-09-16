package com.example.parrot.modules.post.activity.fragment

import android.app.Activity
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.se.omapi.Session
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ListAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.parrot.R
import com.example.parrot.core.SessionController
import com.example.parrot.modules.post.adapter.PostAdapter
import com.example.parrot.modules.post.business.PostBusiness
import com.example.parrot.modules.post.database.PostDatabase
import com.example.parrot.modules.post.model.Post
import com.example.parrot.modules.post.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_home_menu.*
import kotlinx.android.synthetic.main.fragment_register_photo.*
import kotlinx.android.synthetic.main.post_holder.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

const val EXTRA_POST_VIEW_TYPE = "viewType"
const val ESTRA_CONTACT_ID = "contactID"

class HomeFragment: Fragment() {

    val GALLERY_REQUEST_CODE = 1

    lateinit var postViewModel: PostViewModel

    lateinit var postAdapter: PostAdapter

    var foto = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home_menu, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        postViewModel = ViewModelProviders.of(activity!!).get(PostViewModel::class.java)

        setupView()
        subscribeUI()

        postViewModel.getPosts()
    }

    private fun setupView() {

        Glide.with(this)
            .load(SessionController.user?.foto)
            .into(icPhotoPost)

        postAdapter = PostAdapter(
            onCurtir = { postViewModel.curtir(it) },
            onDelete = { postViewModel.deletePost(it) }
        )

        timeLineRecyclerView.adapter = postAdapter

        buttonPost.setOnClickListener {
            postViewModel.mensagem = postField.text.toString()
            postViewModel.doPost()
        }

        includeImage.setOnClickListener {
            pickFromGallery()
        }

    }

    private fun subscribeUI() {

        postViewModel.onError.observe(this, Observer {
            toast(it)
        })

        postViewModel.post.observe(this, Observer {
            postAdapter?.updateListPosts(it)
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

                    postViewModel.foto = imgDecodableString

                }
            }
        }
    }
}

