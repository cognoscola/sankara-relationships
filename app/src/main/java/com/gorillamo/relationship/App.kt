package com.gorillamo.relationship

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.scheduler.*
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App :Application(){


    private val scheduler: Scheduler<Relationship> by inject()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    override fun onCreate() {
        super.onCreate()


        startKoin{

            printLogger()
            androidContext(this@App)
            modules(ModuleProvider.modules)
        }

        //start scheduling
        scheduler.startScheduling(applicationContext,
            listOf(Time(Hour(12), Minute(0),Phase.AM))
        )

    }
}