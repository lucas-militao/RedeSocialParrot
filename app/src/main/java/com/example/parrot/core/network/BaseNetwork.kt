package com.example.parrot.core.network

import android.annotation.SuppressLint
import com.example.parrot.BuildConfig
import com.example.parrot.modules.authentication.model.User
import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

//classe que irá ter os métodos de requisição HTTP
abstract class BaseNetwork {

    companion object {

//        const val BASE_URL = "http://200.19.188.7:3010"
        const val BASE_URL = "http://172.18.9.240:3010"

        const val TOKEN = "token"

        val USER = User()

    }

    enum class RequestStatus{STARTED, FINISHED}

    fun getRetrofitBuilder(): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getOkHttpClientBuilder(HttpLoggingInterceptor.Level.BODY).build())
            .build()
    }

    private fun getOkHttpClientBuilder(loggingLevel: HttpLoggingInterceptor.Level): OkHttpClient.Builder {

        return OkHttpClient.Builder().apply {

            if(BuildConfig.DEBUG) {
                addInterceptor(HttpLoggingInterceptor().apply { level = loggingLevel })
                addNetworkInterceptor(StethoInterceptor())
            }

        }

    }

    @SuppressLint("CheckResult")
    protected fun <T, A> doRequest(
        api: A,
        onSuccess: (T) -> Unit,
        onError: () -> Unit,
        func: A.() -> Observable<T>
    ) {
        api.func()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    onSuccess(response)
                },
                { error ->
                    onError()
                }
            )
    }

}