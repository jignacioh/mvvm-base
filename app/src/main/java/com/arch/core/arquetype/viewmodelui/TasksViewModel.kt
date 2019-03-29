package com.arch.core.arquetype.viewmodelui

import android.util.Log
import androidx.lifecycle.Lifecycle
import com.arch.core.arquetype.base.BaseViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import com.arch.core.arquetype.base.BaseRepository
import com.arch.core.arquetype.model.Model
import com.arch.core.arquetype.model.Task
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

     interface TasksRepository {
        fun getTasks() : MutableList<Task>?
        suspend fun getMoreTasks() : MutableList<Task>?
    }

    open class TasksRepositoryImpl() : BaseRepository(),TasksRepository {

        override suspend fun getMoreTasks() : MutableList<Task>?{
            val service = RetrofitFactory.makeRetrofitService()
            val movieResponse = safeApiCall(
                call = {service.getTasks().await()},
                errorMessage = "Error Fetching Popular Movies"
            )

            return movieResponse?.parts!!.toMutableList()

        }

        override fun getTasks(): MutableList<Task>? {
            val dataList = ArrayList<Task>()
            dataList.add(Task( 1,"Android"))
            dataList.add(Task( 2,"IOs"))
            dataList.add(Task( 3,"Windows"))
            dataList.add(Task( 4,"Native"))

            return dataList.toMutableList()
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