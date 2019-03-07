package com.arch.core.arquetype.viewmodelui

import com.arch.core.arquetype.BaseViewModel
import com.arch.core.arquetype.login.LoginModel

class UiViewModel : BaseViewModel<UINavigator, LoginModel>(){
    override val loginModel: LoginModel
        get() = LoginModel()

    fun doAction() {
        getNavigatorActivity()?.showAction()
   }

}