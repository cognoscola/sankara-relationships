package com.gorillamo.relationship.catalog

import android.app.Application
import org.koin.core.context.startKoin

// application class for espresso tests
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {

        }
    }
}