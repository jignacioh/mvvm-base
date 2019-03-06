package com.arch.core.arquetype.viewmodelui

import androidx.lifecycle.ViewModel
import com.arch.core.arquetype.BaseViewModel

class UiViewModel : BaseViewModel<UINavigator>(){

    fun doAction() {
        getNavigator()?.showAction()
   }

}