package com.arch.core.arquetype.di

import com.arch.core.arquetype.executor.CoroutinesExecutor
import com.arch.core.arquetype.list.ListMovieViewModel
import com.arch.core.arquetype.repository.MoviesRepositoryImpl
import com.arch.core.arquetype.repository.TasksRepositoryImpl
import com.arch.core.arquetype.usecase.*
import com.arch.core.arquetype.viewmodelui.TasksViewModel
import com.arch.core.arquetype.viewmodelui.UiViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {


    // Executor
    single { CoroutinesExecutor() }

    // Repository
    single { TasksRepositoryImpl() }
    single { MoviesRepositoryImpl() }

    // Use Case
    single { TasksUseCaseImpl(getTasksRepositoryImpl = get(),getCoroutinesExecutor = get()) }
    single { MoviesUseCase(getMoviesRepositoryImpl = get(),getCoroutinesExecutor = get())}

    // ViewModel
    viewModel { UiViewModel(get()) }
    viewModel { TasksViewModel(getTasksUseCase = get()) }
    viewModel { ListMovieViewModel(getMoviesUseCase = get()) }





    single<UiViewModel.HelloRepository> { UiViewModel.HelloRepositoryImpl() }
}

val appModules = listOf(appModule)

object RetrofitFactory {
    const val BASE_URL = "http://api.themoviedb.org/3/"

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(RetrofitService::class.java)
    }

}


