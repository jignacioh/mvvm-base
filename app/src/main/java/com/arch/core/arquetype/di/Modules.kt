package com.arch.core.arquetype.di

import com.arch.core.arquetype.live_data.login.LoginRepositoryLD
import com.arch.core.arquetype.live_data.login.LoginViewModelLD
import com.arch.core.arquetype.repository.TasksRepository
import com.arch.core.arquetype.executor.CoroutinesExecutor
import com.arch.core.arquetype.repository.TasksRepositoryImpl
import com.arch.core.arquetype.usecase.*
import com.arch.core.arquetype.retrofit.RetrofitClient
import com.arch.core.arquetype.viewmodelui.TasksViewModel
import com.arch.core.arquetype.viewmodelui.UiViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {


    // Executor
    single { CoroutinesExecutor() }

    // Repository
    single { TasksRepositoryImpl() }

    // Use Case
    single { TasksUseCaseImpl(getTasksRepositoryImpl = get(),getCoroutinesExecutor = get()) }

    single { LoginUseCaseImpl(getcoroutinesExecutor =  get(), repositoryLD = get()) }

    // ViewModel
    viewModel{ UiViewModel(repo = get()) }
    viewModel { LoginViewModelLD(loginUsecase = get()) }
    viewModel { TasksViewModel(getTasksUseCase = get()) }



    single<UiViewModel.HelloRepository> { UiViewModel.HelloRepositoryImpl() }
    single<TasksRepository> { TasksRepositoryImpl() }
    single { LoginRepositoryLD() }
}

val appModules = listOf(appModule)

object RetrofitFactory {
    //const val BASE_URL = "https://api.chucknorris.io"
    const val BASE_URL = "http://192.168.64.2"

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(RetrofitService::class.java)
    }

    fun makeRetrofitServiceGson() : RetrofitService{
        val retrofitClient = RetrofitClient()
        return retrofitClient.retrofitClient()
    }

}


