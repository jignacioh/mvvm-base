package com.arch.core.arquetype.login

import com.arch.core.arquetype.BaseViewModel
import com.arch.core.arquetype.koin.HelloRepository
import com.arch.core.arquetype.viewmodelui.UINavigator

class LoginViewModel(val repo : HelloRepository) : BaseViewModel<UINavigator, LoginModel>(), UINavigator{

    private val model = LoginModel()


    override val loginModel: LoginModel
        get() = model

    fun getUserText(): String {
        return repo.userText()
    }

    fun getPassText():String{
        return repo.passText()
    }
    fun getButtonText(): String {
        return repo.buttonText()
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