package com.example.parrot.core.viewmodel

import androidx.lifecycle.ViewModel
import com.example.parrot.livedata.SingleLiveEvent

abstract class BaseViewModel : ViewModel() {

    val onError = SingleLiveEvent<String>()

}