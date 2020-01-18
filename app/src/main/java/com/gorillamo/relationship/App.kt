package com.gorillamo.relationship

import android.app.Application
import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.google.android.play.core.splitcompat.SplitCompat
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import java.util.concurrent.TimeUnit


class App :Application(){

    val WORK_TAG = "relationshipUpdater"


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


        //lets schedule via the Work Manager
        val workManager = WorkManager.getInstance(this)

        //now lets schedule it
        val workBuilder = PeriodicWorkRequestBuilder<RelationshipScheduler>(1, TimeUnit.DAYS)
        workBuilder.addTag(WORK_TAG)
        val work = workBuilder.build()
        workManager.enqueueUniquePeriodicWork(WORK_TAG,ExistingPeriodicWorkPolicy.KEEP,work)
    }

}