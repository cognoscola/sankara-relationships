package com.gorillamo.scheduler.alarm

import android.content.Context
import com.gorillamo.scheduler.*

import java.util.*

private const val WAKE_UP_HOUR = "wake_up_hour"
private const val WAKE_UP_MINUTE = "wake_up_minute"
private const val WAKE_PHASE = "wake_phase"
private const val SLEEP_HOUR = "sleep_hour"
private const val SLEEP_MINUTE = "sleep_minute"
private const val SLEEP_PHASE = "sleep_phase"

//TODO finish commenting these functions
//TODO truncate these codes

fun Context.getSavedWakeUpTime():Calendar{

    val prefs = getLocalSettings()
    return Calendar.getInstance().apply {
        setTimeToCalendar(prefs.getWakeTime(Identifier(0)),1)
    }
}

fun Context.setWakeTimeToCalendarAndStore(cal: Calendar,time: Time){

    //The user is very likely to experience a Wake up alarm on the next day.
    //since they have already woken up.
    cal.setTimeToCalendar(time,1)
    saveWakeTime(time)
}


fun Context.getSavedSleepTime():Calendar{

    val prefs = getLocalSettings()
    return Calendar.getInstance().apply {
        setTimeToCalendar(prefs.getSleepTime(Identifier(0)),0)
    }
}


fun Context.setSleepTimeToCalendarAndStore(cal: Calendar, time:Time){

    //days Ahead is 0. It is likely that they will schedule a sleep alarm
    //before they sleep
    cal.setTimeToCalendar(time,0)
    saveSleepTime(time)
}


fun Calendar.setTimeToCalendar(time:Time, daysAhead:Int){
    timeInMillis = System.currentTimeMillis()
    set(Calendar.AM_PM,time.phase.toInt())
    set(Calendar.HOUR, time.hour.get())
    set(Calendar.MINUTE,time.minute.get())
    add(Calendar.DATE,daysAhead) //specify to fire TOMORROW
}