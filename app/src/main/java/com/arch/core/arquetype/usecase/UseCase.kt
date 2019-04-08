package com.arch.core.arquetype.usecase

import com.arch.core.arquetype.executor.Executor


open abstract class UseCase (private val executor: Executor){

    fun uiExecute(uiFun:suspend ()->Unit){
        executor.uiExec{uiFun()}
    }
    fun asyncExecute(asyncFun:suspend()->Unit){
        executor.asyncExec {asyncFun()}
    }

}