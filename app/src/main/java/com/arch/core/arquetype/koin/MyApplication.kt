package com.arch.core.arquetype.koin

import android.app.Application
import com.arch.core.arquetype.live_data.login.LoginViewModelLD
import com.arch.core.arquetype.login.LoginViewModel
import org.koin.android.ext.android.startKoin
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

class MyApplication : Application() {

    val appModule = module{
        single<HelloRepository> { HelloRepositoryImpl() }

        factory { MySimplePresenter(get()) }

        viewModel { LoginViewModel(get()) }
        viewModel { LoginViewModelLD(get()) }

    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}