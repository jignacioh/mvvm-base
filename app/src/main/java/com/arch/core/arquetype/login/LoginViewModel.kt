package com.arch.core.arquetype.login

import com.arch.core.arquetype.BaseViewModel
import com.arch.core.arquetype.viewmodelui.UINavigator

class LoginViewModel : BaseViewModel<UINavigator, LoginModel>(), UINavigator{

    private val model = LoginModel()

    override val loginModel: LoginModel
        get() = model

    fun getUserText(): String {
        return "Nombre de Usuario"
    }

    fun getPassText():String{
        return "Contraseña"
    }
    fun getButtonText(): String {
        return "Iniciar"
    }

    override fun showAction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun campoVacio(){
        getNavigatorActivity()?.campoVacio()
    }

    override fun successLogin(){
        getNavigatorActivity()?.successLogin()
    }


    fun validarUsuario(user:String, pass:String){
        loginModel.setNavigatorViewModel(this)
        loginModel.procesarUsuario(user, pass)
    }

}