package com.arch.core.arquetype.live_data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorChangerViewModel : ViewModel() {

    val colorResource = MutableLiveData<Int>()

    init {
        //colorResource.value = 0xFFF
        Log.i("Live Data", "Metodo Init()")
        colorResource.value = 0xf00
    }
}