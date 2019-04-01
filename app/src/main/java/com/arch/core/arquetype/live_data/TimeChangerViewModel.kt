package com.arch.core.arquetype.live_data

import android.database.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.*

class TimeChangerViewModel : ViewModel() {

    val timeChanger = MutableLiveData<Long>()

    init {
        timeChanger.value = System.currentTimeMillis()

    }

    private fun startTimer(){
       // val disposable = Observable.
    }
}