package com.arch.core.arquetype

import java.lang.ref.WeakReference

abstract class BaseModel<N> {

    private var mNavigatorViewModel: WeakReference<N>? = null

    fun getNavigatorViewModel(): N? {
        return mNavigatorViewModel?.get()
    }

    fun setNavigatorViewModel(navigator: N) {
        this.mNavigatorViewModel = WeakReference(navigator)
    }

}