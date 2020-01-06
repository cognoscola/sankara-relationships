package com.gorillamo.relationship

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.domain.receivers.AlarmReceiver
import com.gorillamo.scheduler.*
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber


class App :Application(){


    private val scheduler: Scheduler<Relationship> by inject()

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        //For JakeWharton java.time library (below API 23
        AndroidThreeTen.init(this);
        Timber.plant(Timber.DebugTree())
        startKoin{
            printLogger()
            androidContext(this@App)
            modules(ModuleProvider.modules)
        }

        scheduler.startScheduling(this,
            listOf(
                Task.newTask(Identifier(0))
                    .run(AlarmReceiver::class.java)
                    .at(Time(Identifier(0),Hour(8), Minute(0),Phase.AM)))
        )

        //lets schedul


    }

    fun stopScheduling(){

        scheduler.stopScheduling(this,Identifier(0))
    }
}