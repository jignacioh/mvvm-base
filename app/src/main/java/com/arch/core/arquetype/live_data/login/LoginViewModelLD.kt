package com.arch.core.arquetype.live_data.login

import com.arch.core.arquetype.base_con_binding.BaseViewModel
import com.arch.core.arquetype.login.LoginModel
import com.arch.core.arquetype.viewmodelui.UINavigator
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LoginViewModelLD(val repository : LoginRepositoryLD) : BaseViewModel<UINavigator, LoginModel>(){

    private var parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main

    private val scope = CoroutineScope(coroutineContext)


    override val loginModel: LoginModel
        get() = LoginModel()


    fun changeFlagLogin(){
        scope.launch {
            val responsePojo = repository.validarUser()
            if (responsePojo != null)
                getNavigatorActivity()?.successLogin()
            else {
                getNavigatorActivity()?.showError("Error en servidor")
            }
        }
    }

    fun cancelCoRoutine(){
        if (parentJob.isActive) {
            parentJob.cancelChildren()
        }
    }
}
