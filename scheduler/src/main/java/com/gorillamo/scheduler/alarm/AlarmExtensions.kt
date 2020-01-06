package com.gorillamo.scheduler.alarm

import android.app.AlarmManager
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.gorillamo.scheduler.Task

//import com.gorillamo.scheduler.receiver.SimpleBootReceiver

//TODO Create a more general alarm scheduling system, not just wake up/sleep
//import com.gorillamo.scheduler.DaySchedulerAdapter.Companion.EVENT_SLEEP
//import com.gorillamo.scheduler.DaySchedulerAdapter.Companion.EVENT_WAKEUP
//import com.gorillamo.scheduler.DaySchedulerAdapter.Companion.SLEEP_INTENT_CODE
//import com.gorillamo.scheduler.DaySchedulerAdapter.Companion.WAKE_UP_INTENT_CODE

/**
 * Will enable the alarm to be set despite device shutdown
 */
/*
fun Context.alarmEnableWakeUpPersistent(task:Task){

    alarmEnableWakeUp(task)
//    SimpleBootReceiver.enableBootReceiver(this)
}
*/


/**
 * Enable sleep alarm to be set despite device shutdown
 * @receiver Context
 */
/*
fun Context.alarmEnableSleepPersistent(task: Task){
    alarmEnableSleep(task)
//    SimpleBootReceiver.enableBootReceiver(this)
}
*/

/**
 * Disable persistent alarms
 * @receiver Context
 */
/*
fun Context.alarmDisableWakePersistent(task:Task? = null){
    alarmDisableWakeUp()
//    SimpleBootReceiver.disableBootReciver(this)
}
*/

/**
 * disable persistent ability of alarm
 * @receiver Context
 */
/*
fun Context.alarmDisableSleepPersistent(task:Task){
    alarmDisableSleep(task)
//    SimpleBootReceiver.disableBootReciver(this)
}
*/


/**
 * Convenience method to enable the alarm.
 * The time to set alarm will be fetched from preferences
 */
/*
fun Context.alarmEnableWakeUp(task:Task){

    if (!isAlarmWorking(task)) {
        alarmSetRepeatWithCal(task,true)
    }else{
        Log.d("AlarmExtension alarmEnableWakeUp","Alarm Already Activity")
        Toast.makeText(this,"Alarm Already active",Toast.LENGTH_SHORT).show()
    }
}
*/

/*
fun Context.alarmEnableSleep(task:Task){
    alarmSetRepeatWithCal(task,false)
}
*/

/*
fun Context.isAlarmWorking(task:Task):Boolean {

    return (createAlarmExistentPendingIntent(
        createAlarmIntent(task.taskClass, EVENT_WAKEUP),
        WAKE_UP_INTENT_CODE
    ) != null).and(isWakeAlarmSet())
}
*/

/**
 * Convenience disable the Wake up Alarm.
 */
/*
fun Context.alarmDisableWakeUp(){
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    alarmManager.cancel(
        createAlarmPendingIntent(
            createAlarmIntent(
                Class.forName("com.gorillamo.relationship.domain.receivers.AlarmReceiver"),
                EVENT_WAKEUP
            ), WAKE_UP_INTENT_CODE
        )
    )
    saveAlarmWakeStatus(false)
}
*/

/**
 *
 * @receiver Context
 */
/*
fun Context.alarmDisableSleep(task:Task) {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.cancel(
        createAlarmPendingIntent(
            createAlarmIntent(
                task.taskClass,
                EVENT_SLEEP
            ), SLEEP_INTENT_CODE
        )
    )
    saveAlarmSleepStatus(false)

}
*/

/**
 * Set the repeating alarm with the given calendar
 * @param cal is the calendar object containing information about when to set alarm
 * @param wakeUp determines if this alarm will be a wake up or a sleep notification
 */
/*
fun Context.alarmSetRepeatWithCal(task: Task, wakeUp:Boolean){
    val alarmManager = getAlarmService()

    alarmManager.setInexactRepeating(
        AlarmManager.RTC_WAKEUP,
        //Lets just test that it triggers
        task.time.toCalendar().timeInMillis,
        AlarmManager.INTERVAL_DAY,

        createAlarmPendingIntent(createAlarmIntent(
            task.taskClass,
            if(wakeUp) EVENT_WAKEUP else EVENT_SLEEP
        ),if(wakeUp){ WAKE_UP_INTENT_CODE} else SLEEP_INTENT_CODE)

    )

    if(wakeUp)saveAlarmWakeStatus(true)else saveAlarmSleepStatus(true)
}
*/

fun Context.getAlarmService():AlarmManager{
    return getSystemService(Context.ALARM_SERVICE) as AlarmManager
}