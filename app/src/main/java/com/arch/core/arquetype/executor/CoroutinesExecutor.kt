package com.arch.core.arquetype.executor

import com.arch.core.arquetype.executor.Executor
import kotlinx.coroutines.*

 class CoroutinesExecutor: Executor {


    override fun uiExec(uiFun:suspend ()->Unit) {
        GlobalScope.launch(Dispatchers.Main) {
            uiFun()
        }
    }

    override fun asyncExec(asyncFun:suspend()->Unit) {
        GlobalScope.async(Dispatchers.Default) {
            asyncFun()
        }
    }

}
