package com.arch.core.arquetype.list

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import com.arch.core.arquetype.base.BaseViewModel
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.repository.network.Failure
import com.arch.core.arquetype.usecase.TasksUseCaseImpl
import com.arch.core.arquetype.viewmodelui.TasksNavigator
import kotlinx.coroutines.cancel

class ListMovieViewModel (private val getTasksUseCase: TasksUseCaseImpl) : BaseViewModel<TasksNavigator>() {


    var tasksLiveData = MutableLiveData<MutableList<Task>>()


    sealed class TasksState {
        object Loading : TasksState()
        object Empty : TasksState()
        data class Success(val tasks: List<Task>) : TasksState()
    }

    val state = MutableLiveData<TasksState>().apply {
        this.value = TasksState.Loading
    }

    fun addOne() {


        getNavigator()!!.showAction(true)
        val params = TasksUseCaseImpl.Params(50)
        getTasksUseCase.invoke(viewModelScope,params){
            it.either(::handleFailure,::handleSuccess)
        }

        /*getTasksUseCase.execute(
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
            })*/
    }

    private fun handleSuccess(list: List<Task>) {
        when {
            list.isEmpty() -> state.value = TasksState.Empty
            list.isNotEmpty() -> state.value = TasksState.Success(mapToPresentation(list))
        }
    }

    private fun handleFailure(failure: Failure) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        Log.e("ERROR","ERROR")
    }

    private fun mapToPresentation(friends: List<Task>): List<Task> {
        return friends.map { Task(2, it.itemName!!) }
    }

    fun cancelRequest(){
        getNavigator()?.showAction(false)
        /*if (parentJob.isActive) {
            parentJob.cancelChildren()
        }*/

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
        viewModelScope.cancel()
    }

}