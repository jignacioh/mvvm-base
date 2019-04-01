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

    var popularMoviesLiveData = MutableLiveData<MutableList<Task>>()

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)


    fun addOne() {

        scope.launch {
            val popularMovies = repository.getMoreTasks()
            if (popularMovies != null) {
                popularMoviesLiveData.postValue(popularMovies)
            }
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun init() {
        Log.i("onCreate","onCreate viewModel")
        popularMoviesLiveData.postValue( repository.getTasks())

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