package com.arch.core.arquetype.base_con_binding

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import java.lang.ref.WeakReference

abstract class BaseViewModel<N, M : BaseRepository<N>> : ViewModel() {

    abstract val loginModel:M

    private val mIsLoading = ObservableBoolean(false)

    private var mNavigator: WeakReference<N>? = null


    fun getIsLoading(): ObservableBoolean {
        return mIsLoading
    }

    fun setIsLoading(isLoading: Boolean) {
        mIsLoading.set(isLoading)
    }


    fun getNavigatorActivity(): N? {
        return mNavigator?.get()
    }

    fun setNavigatorActivity(navigator: N) {
        this.mNavigator = WeakReference(navigator)
    }


}
