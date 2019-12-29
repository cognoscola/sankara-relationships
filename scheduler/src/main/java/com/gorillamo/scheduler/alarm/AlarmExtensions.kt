package com.gorillamo.scheduler.alarm

import android.app.AlarmManager
import android.content.Context
import com.gorillamo.scheduler.receiver.SimpleBootReceiver
import java.util.*


//TODO Create a more general alarm scheduling system, not just wake up/sleep

/**
 * Will enable the alarm to be set despite device shutdown
 */
fun Context.alarmEnableWakeUpPersistent(cal:Calendar){

    alarmEnableWakeUp(cal)
    SimpleBootReceiver.enableBootReceiver(this)
}


/**
 * Enable sleep alarm to be set despite device shutdown
 * @receiver Context
 */
fun Context.alarmEnableSleepPersistent(cal:Calendar){
    alarmEnableSleep(cal)
    SimpleBootReceiver.enableBootReceiver(this)
}

/**
 * Disable persistent alarms
 * @receiver Context
 */
fun Context.alarmDisableWakePersistent(){
    alarmDisableWakeUp()
    SimpleBootReceiver.disableBootReciver(this)
}

/**
 * disable persistent ability of alarm
 * @receiver Context
 */
fun Context.alarmDisableSleepPersistent(){
    alarmDisableSleep()
    SimpleBootReceiver.disableBootReciver(this)
}


/**
 * Convenience method to enable the alarm.
 * The time to set alarm will be fetched from preferences
 */
fun Context.alarmEnableWakeUp(cal:Calendar){
    alarmSetRepeatWithCal(cal,true)
}

fun Context.alarmEnableSleep(cal:Calendar){
    alarmSetRepeatWithCal(cal,false)
}


/**
 * Convenience disable the Wake up Alarm.
 */
fun Context.alarmDisableWakeUp(){
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(createWakeUpAlarmPendingIntent())
    saveAlarmWakeStatus(false)
}

/**
 *
 * @receiver Context
 */
fun Context.alarmDisableSleep(){
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(createWakeUpAlarmPendingIntent())
    saveAlarmSleepStatus(false)

}

/**
 * Set the repeating alarm with the given calendar
 * @param cal is the calendar object containing information about when to set alarm
 * @param wakeUp determines if this alarm will be a wake up or a sleep notification
 */
fun Context.alarmSetRepeatWithCal(cal:Calendar, wakeUp:Boolean){
    val alarmManager = getAlarmService()

    alarmManager.setInexactRepeating(
        AlarmManager.RTC_WAKEUP,
        cal.timeInMillis,
        AlarmManager.INTERVAL_DAY,
        if(wakeUp){
            createWakeUpAlarmPendingIntent()} else{
            createSleepAlarmPendingIntent()
        }
    )

    if(wakeUp)saveAlarmWakeStatus(true)else saveAlarmSleepStatus(true)
}

fun Context.getAlarmService():AlarmManager{
    return getSystemService(Context.ALARM_SERVICE) as AlarmManager
}