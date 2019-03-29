package com.arch.core.arquetype.di

import android.app.Application
import com.arch.core.arquetype.viewmodelui.appModules
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
        /*startKoin{androidContext(this@BaseApp)
        androidLogger()
        modules(appModules)}*/
    }
}