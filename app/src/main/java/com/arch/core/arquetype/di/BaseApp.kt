package com.arch.core.arquetype.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        //startKoin (this, appModules)

        startKoin {
            //printLogger
            androidLogger()

            androidContext(this@BaseApp)

            modules(appModules)
        }
    }
}