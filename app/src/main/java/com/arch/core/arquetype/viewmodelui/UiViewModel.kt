package com.arch.core.arquetype.viewmodelui

import com.arch.core.arquetype.base_con_binding.BaseViewModel
import com.arch.core.arquetype.login.LoginModel

class UiViewModel : BaseViewModel<UINavigator, LoginModel>(){
    override val loginModel: LoginModel
        get() = LoginModel()

    fun doAction() {
        getNavigatorActivity()?.showAction()
   }

}