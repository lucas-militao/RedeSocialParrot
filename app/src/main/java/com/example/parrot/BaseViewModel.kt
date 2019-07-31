package com.example.parrot

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    val onError = SingleLiveEvent<String>()

}