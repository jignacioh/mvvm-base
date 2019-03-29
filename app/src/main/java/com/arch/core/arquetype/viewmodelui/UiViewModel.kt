package com.arch.core.arquetype.viewmodelui

import com.arch.core.arquetype.base.BaseViewModel

class UiViewModel(val repo:HelloRepository) : BaseViewModel<UINavigator>(){

    fun doAction() {



        getNavigator()?.showAction()
   }

    open interface HelloRepository {
        fun giveHello(): String
    }

    open class HelloRepositoryImpl() : HelloRepository {
        override fun giveHello() = "Hello Koin"
    }
}