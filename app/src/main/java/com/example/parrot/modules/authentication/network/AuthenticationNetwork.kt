package com.example.parrot.modules.authentication.network

import com.example.parrot.core.network.BaseNetwork
import com.example.parrot.modules.authentication.model.User
import com.example.parrot.modules.authentication.model.UserWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

object AuthenticationNetwork : BaseNetwork() {

    private val API by lazy { getRetrofitBuilder().create(AuthenticationAPI::class.java) }

    fun requestLogin(
            email: String,
            password: String,
            onSuccess: (response: Response<User>) -> Unit,
            onError: () -> Unit
    ) {

        val userWrapper = UserWrapper(email, password)


        API.requestLogin(userWrapper).enqueue(object : Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {

                if (response.isSuccessful) {
                    onSuccess(response)
                } else {
                    onError()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onError()
                t.printStackTrace()
            }
        })
    }

    fun requestRegisterUser(
            nome: String,
            username: String,
            email: String,
            password: String,
            foto: String?,
            onSuccess: () -> Unit,
            onError: () -> Unit
    ) {

//        val userWrapper = UserWrapper().apply {
//            this.nome = nome
//            this.username = username
//            this.email = email
//            this.password = password
//            this.passwordConfirmation = password
//            this.foto = foto
//        }

        var file = File(foto)

        var fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)

        var part = MultipartBody.Part.createFormData("foto", file.name, fileRequestBody)

        API.requestRegisterUser(nome,
            email, username, password, part)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {
                            onSuccess()
                        },
                        {
                            onError()
                        }
                )

    }

    fun updateUser(nome: String,
                   username: String,
                   email: String,
                   senha: String,
                   foto: String,
                   onSuccess: (user: User) -> Unit,
                   onError: () -> Unit) {

        val userWrapper = UserWrapper().apply {
            this.nome = nome
            this.email = email
            this.foto = foto
            this.password = senha
            this.passwordConfirmation = senha
            this.username = username
        }


        API.requestUpdateUser(userWrapper).enqueue(object : retrofit2.Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                onError()
                t.printStackTrace()
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    onError()
                }
            }
        })

    }

    fun uploadToServer(filepath : String,
                               onSuccess : (response: ResponseBody) -> Unit,
                               onError: () -> Unit) {

        val retrofit = getRetrofitBuilder()

        var file = File(filepath)

        var fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)

        var part = MultipartBody.Part.createFormData("upload", file.name, fileRequestBody)

        var description = RequestBody.create(MediaType.parse("text/plain"), "image-type")

        var call = API.uploadImage(part, description)

        call.enqueue(object: Callback<ResponseBody> {
            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {}

            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {}
        })

    }
}