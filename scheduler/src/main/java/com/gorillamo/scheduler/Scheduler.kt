package com.gorillamo.scheduler

import android.app.AlarmManager
import android.content.Context
import java.util.*

interface  Scheduler<T>{

    fun getItemsDue(input: List<T>):List<T>

    companion object{
        fun <T> getInstance(converter:(T)->SchedulingItem<T>):Scheduler<T>{
           return DaySchedulerAdapter(converter)
        }
    }
}