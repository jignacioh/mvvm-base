package com.arch.core.arquetype.list

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.viewModelScope
import com.arch.core.arquetype.base.BaseViewModel
import com.arch.core.arquetype.model.ListMovie
import com.arch.core.arquetype.model.Movie
import com.arch.core.arquetype.model.Task
import com.arch.core.arquetype.repository.network.Failure
import com.arch.core.arquetype.usecase.MoviesUseCase
import com.arch.core.arquetype.usecase.TasksUseCaseImpl
import com.arch.core.arquetype.viewmodelui.TasksNavigator
import kotlinx.coroutines.cancel

class ListMovieViewModel (private val getMoviesUseCase: MoviesUseCase) : BaseViewModel<ListMovieNavigator>() {

    sealed class MovieState {
        object None : MovieState()
        object Loading : MovieState()
        object Empty : MovieState()
        data class Success(val tasks: List<Movie>) : MovieState()
    }

    val state = MutableLiveData<MovieState>().apply {
        this.value = MovieState.None
    }

    fun addTwo() {

        state.value = MovieState.Loading
        //getNavigator()!!.showAction(true)
        val params = MoviesUseCase.Params(50)
        getMoviesUseCase.invoke(viewModelScope,params){
            it.either(::handleFailure,::handleSuccess)
        }

    }

    private fun handleSuccess(list: ListMovie) {
        when {
            list.results!!.isEmpty() -> state.value = MovieState.Empty
            list.results!!.isNotEmpty() -> state.value = MovieState.Success(mapToPresentation(list))
        }
    }

    private fun handleFailure(failure: Failure) {
        state.value = MovieState.None
        Log.e("ERROR",failure.toString())
    }

    private fun mapToPresentation(friends: ListMovie): List<Movie> {
        return friends.results!!.map {
            Movie(it.id,it.nameMovie,it.yearMovie)
        }
    }

    fun cancel(){
        state.value = MovieState.None

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