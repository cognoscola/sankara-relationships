package com.gorillamo.scheduler

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SchedulerAlarmTests{

    lateinit var context:Context
    @Before
    fun setUp(){
        context = ApplicationProvider.getApplicationContext<Context>()

    }


    @Test
    fun verifyTaskIsOnlyScheduledOnce(){
        /*     val mockContext = mock(Context::class.java)
             val pi = PendingIntent.getBroadcast(mockContext, 0, Intent("DoNothing"), 0)
             val tasks = listOf(
                 Task.newTask(Identifier(0)).run(MockBroadCast::class.java).at(
                     Time(Identifier(0),Hour(3),Minute(0), Phase.AM)
                 )
             )


             `when`(mockContext.createAlarmPendingIntent(any(Intent::class.java), anyInt())).thenReturn(null)

             scheduler.startScheduling(mockContext, tasks)

             `when`(mockContext.createAlarmPendingIntent(any(Intent::class.java), anyInt())).thenReturn(pi)

             scheduler.startScheduling(mockContext, tasks)

             `verify`(mockContext.isAlarmWorking(), atLeast(2)).*/

    }


}