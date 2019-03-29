package com.arch.core.arquetype.di

import com.arch.core.arquetype.repository.TasksRepository
import com.arch.core.arquetype.repository.TasksRepositoryImpl
import com.arch.core.arquetype.viewmodelui.TasksViewModel
import com.arch.core.arquetype.viewmodelui.UiViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


val appModule = module {
    viewModel{ UiViewModel(get()) }
    viewModel { TasksViewModel(get()) }

    single<UiViewModel.HelloRepository> { UiViewModel.HelloRepositoryImpl() }
    single<TasksRepository> { TasksRepositoryImpl() }
}

val appModules = listOf(appModule)

object RetrofitFactory {
    const val BASE_URL = "http://192.168.0.151"

    fun makeRetrofitService(): RetrofitService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(RetrofitService::class.java)
    }

}

