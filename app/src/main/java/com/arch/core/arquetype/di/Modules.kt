package com.arch.core.arquetype.di

import com.arch.core.arquetype.executor.CoroutinesExecutor
import com.arch.core.arquetype.repository.TasksRepositoryImpl
import com.arch.core.arquetype.usecase.*
import com.arch.core.arquetype.viewmodelui.TasksViewModel
import com.arch.core.arquetype.viewmodelui.UiViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {


    // Executor
    single { CoroutinesExecutor() }

    // Repository
    single { TasksRepositoryImpl() }

    // Use Case
    single { TasksUseCaseImpl(getTasksRepositoryImpl = get(),getCoroutinesExecutor = get()) }

    // ViewModel
    viewModel { UiViewModel(get()) }
    viewModel { TasksViewModel(getTasksUseCase = get()) }




    single<UiViewModel.HelloRepository> { UiViewModel.HelloRepositoryImpl() }
}

val appModules = listOf(appModule)

object RetrofitFactory {
    const val BASE_URL = "http://192.168.137.133"

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(RetrofitService::class.java)
    }

}


