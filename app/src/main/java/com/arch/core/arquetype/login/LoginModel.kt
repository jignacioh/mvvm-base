package com.arch.core.arquetype.login

import com.arch.core.arquetype.BaseModel
import com.arch.core.arquetype.viewmodelui.UINavigator

class LoginModel : BaseModel<UINavigator>() {

    fun procesarUsuario(user: String, pass: String){
        if (user.isNullOrEmpty() || pass.isNullOrEmpty()){
            getNavigatorViewModel()?.campoVacio()
        }
        else{
            Thread.sleep(2000)
            getNavigatorViewModel()?.successLogin()
        }
    }
}