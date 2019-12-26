package com.gorillamo.scheduler

interface  Scheduler<T>{
    fun getItemsDue(input: List<T>):List<T>
}