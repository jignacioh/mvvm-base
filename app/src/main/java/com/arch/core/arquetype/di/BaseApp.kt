package com.arch.core.arquetype.di

import android.app.Application
import org.koin.android.ext.android.startKoin

class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}