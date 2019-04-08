package com.arch.core.arquetype.executor

interface Executor {
    fun uiExec(uiFun:suspend ()->Unit)
    fun asyncExec(asyncFun:suspend()->Unit)
}