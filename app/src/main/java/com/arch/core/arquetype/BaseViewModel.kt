package com.arch.core.arquetype

import android.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel

import java.lang.ref.WeakReference

abstract class BaseViewModel : ViewModel() {

    private val mIsLoading = ObservableBoolean(false)

    fun getmIsLoading(): ObservableBoolean {
        return mIsLoading
    }


    abstract fun getViewModel():ViewModel
}
