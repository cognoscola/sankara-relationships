package com.gorillamo.scheduler

import java.awt.Point

interface  Scheduler<T>{

    fun getItemsDue(input: List<T>):List<T>

    companion object{
        fun <T> getInstance(converter:(T)->SchedulingItem<T>):Scheduler<T>{
           return DaySchedulerAdapter(converter)
        }
    }
}