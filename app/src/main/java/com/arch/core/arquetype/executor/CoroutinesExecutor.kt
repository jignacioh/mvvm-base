package com.arch.core.arquetype.executor

import android.util.Log
import com.arch.core.arquetype.executor.Executor
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CoroutinesExecutor: Executor {

    private val parentJob = SupervisorJob()

    private val exceptionHandler = CoroutineExceptionHandler {
            _, throwable ->

        Log.e("ERROR","ERROR",throwable)
        // parentJob.cancel()
        //getNavigator()?.showAction(false)

    }

    private val coroutineContext: CoroutineContext
        get() =  parentJob+ Dispatchers.Main + CoroutineName("Coroutine") + exceptionHandler

    private val scope = CoroutineScope(coroutineContext)

    override fun uiExec(uiFun:suspend ()->Unit) {
        scope.launch {
            uiFun()
        }
    }

    override fun asyncExec(asyncFun:suspend()->Unit) {
        scope.async(Dispatchers.Default) {
            asyncFun()
        }
    }

}
