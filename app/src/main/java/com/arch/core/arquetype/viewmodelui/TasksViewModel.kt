package com.arch.core.arquetype.viewmodelui

import android.util.Log
import androidx.lifecycle.Lifecycle
import com.arch.core.arquetype.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.di.RetrofitFactory
import com.arch.core.arquetype.model.Model
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.repository.TasksRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext


class TasksViewModel (val repository : TasksRepository) : BaseViewModel<TasksNavigator>() {

    var arrayList = MutableLiveData<Model>()

    var tasksLiveData = MutableLiveData<MutableList<Task>>()

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default+CoroutineName("sd")

    private val scope = CoroutineScope(coroutineContext)

    val vTask = Task(3)

    fun addOne() {

        val(id)=vTask

        print(vTask.component1())

        var vTask2=vTask.copy(id=4)

        scope.launch {
            val tasks = repository.getMoreTasks()
            if (tasks != null) {
                tasksLiveData.postValue(tasks)
            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        Log.i("onCreate","onCreate viewModel")
        tasksLiveData.postValue( repository.getTasks())

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun destroy(){
        Log.i("onDestroy","onDestroy viewModel")
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}