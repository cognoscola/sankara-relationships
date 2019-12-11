package com.gorillamo.app.relationship

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application(){


    override fun onCreate() {
        super.onCreate()

        startKoin{

            printLogger()
            androidContext(this@App)
            modules(ModuleProvider.modules)
        }

    }
}