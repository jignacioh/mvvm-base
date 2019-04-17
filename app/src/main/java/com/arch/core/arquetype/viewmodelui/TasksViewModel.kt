package com.arch.core.arquetype.viewmodelui

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.arch.core.arquetype.base.BaseViewModel
import com.arch.core.arquetype.model.Model
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.usecase.TasksUseCaseImpl
import com.arch.core.arquetype.di.fold
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class TasksViewModel (private val getTasksUseCase: TasksUseCaseImpl) : BaseViewModel<TasksNavigator>() {

    var arrayList = MutableLiveData<Model>()

    var tasksLiveData = MutableLiveData<MutableList<Task>>()

    private val parentJob = SupervisorJob()

    private val exceptionHandler = CoroutineExceptionHandler {
            _, _ ->

        Log.e("ERROR","ERROR")
       // parentJob.cancel()
        getNavigator()?.showAction(false)

    }

    private val coroutineContext: CoroutineContext
        get() =  parentJob+ Dispatchers.Main + CoroutineName("Coroutine") + exceptionHandler

    private val scope = CoroutineScope(coroutineContext)

    val vTask = Task(3)

    fun addOne() {
        getNavigator()!!.showAction(true)
        getTasksUseCase.execute(
            onResult = { result ->
                result.fold(
                    {
                        getNavigator()!!.showAction(false)
                    },
                    {
                        tasks ->
                        getNavigator()!!.showAction(false)
                        tasksLiveData.postValue(tasks)}
                    )
            })
    }

    fun cancelRequest(){
        getNavigator()?.showAction(false)
        if (parentJob.isActive) {
            parentJob.cancelChildren()
        }

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {

        //tasksLiveData.postValue( repository.getTasks())

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        Log.i("onDestroy","onDestroy viewModel")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("onCleared","onCleared viewModel")
        parentJob.cancel()
    }
}