package com.gorillamo.relationship.domain

import android.app.Application
import android.util.Log
import org.koin.core.context.startKoin

// application class for espresso tests
class App : Application() {

    @Suppress("unused")
    private val tag:String = App::class.java.name
    override fun onCreate() {
        super.onCreate()

        startKoin {}
    }
}