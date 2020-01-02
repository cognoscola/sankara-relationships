package com.gorillamo.scheduler

import android.app.AlarmManager
import android.content.Context
import java.util.*

interface  Scheduler<T>{

    //TODO move this out of here
    fun getItemsDue(input: List<T>):List<T>

    fun startScheduling(context: Context, tasks:List<Task>)

    fun stopScheduling(context: Context, identifier: Identifier)

    companion object{
        fun <T> getInstance(converter:(T)->SchedulingItem<T>):Scheduler<T>{
           return DaySchedulerAdapter(converter)
        }
    }
}