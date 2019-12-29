package com.gorillamo.scheduler.alarm

import android.annotation.TargetApi
import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.SharedPreferences
import android.service.notification.StatusBarNotification
import android.util.Log
import com.gorillamo.scheduler.*
import java.util.*
import kotlin.collections.ArrayList

private const val LOCAL_SETTINGS ="local_app_settings"
private const val isWakeAlarmActive= "isWakeAlarmActive"
private const val isSleepAlarmActive= "isSleepAlarmActive"

const val isRestAlarmActive = "isRestAlarmActive"

const val isTimerAlarmActive = "isTimerAlarmActive"
const val isTimerAlarmTriggered = "isTimerAlarmTriggered"
const val isBreakAlarmTriggered = "isBreakAlarmTriggered"
const val selectedMinutesTimer = "timerMinute"

//TODO allow user to turn this feature on or off
private const val isActivityRecognictionOn ="isRecognitionOn"

//TODO remove other copies of these values
private const val WAKE_UP_HOUR = "wake_up_hour"
private const val WAKE_UP_MINUTE = "wake_up_minute"
private const val WAKE_PHASE = "wake_phase"

private const val SLEEP_HOUR = "sleep_hour"
private const val SLEEP_MINUTE = "sleep_minute"
private const val SLEEP_PHASE = "sleep_phase"
//TODO END

//Task related information
private const val TASK_INCOMPLETE = "INCOMPLETE" // tasks not yet completed
private const val TASK_COMPLETE = "COMPLETE" //which tasks completed
private const val TASK_ORDER = "order" //task order
private const val IS_ACTIVE = "isActive" //wether the app is currently working on not

private const val KEY_TASK_VISIBLE ="taskShowing"

private const val ONBOARDING = "isOnboarding"

private const val PREF_STUBBORN = "pref_notification_stubborn"


fun Context.getLocalSettings():SharedPreferences{
    //later investigate cost of retrieving shared preferences
    return getSharedPreferences(LOCAL_SETTINGS, Activity.MODE_PRIVATE)
}


/**
 * Convenient method save into preferences the alarm time
 */
//TODO Truncate these 2 methods below
fun Context.saveWakeTime(time:Time){
    val prefs= getLocalSettings()
    prefs
        .edit()
        .putInt(WAKE_UP_HOUR,time.hour.get())
        .putInt(WAKE_UP_MINUTE,time.minute.get())
        .putInt(WAKE_PHASE,time.phase.toInt())
        .apply()
}

fun Context.saveSleepTime(time:Time){
    val prefs= getLocalSettings()
    prefs
        .edit()
        .putInt(SLEEP_HOUR,time.hour.get())
        .putInt(SLEEP_MINUTE,time.minute.get())
        .putInt(SLEEP_PHASE,time.phase.toInt())
        .apply()
}

fun Context.saveTimerTime(timeMillis:Long){

    val prefs = getLocalSettings()
    prefs
        .edit()
        .putLong(selectedMinutesTimer,timeMillis)
        .apply()
}

fun Context.getTimerTime():Long{
    return getLocalSettings().getLong(selectedMinutesTimer,0)
}


//clean truncate these and accept one parameter
fun Context.isWakeAlarmSet():Boolean{
    return getLocalSettings().getBoolean(isWakeAlarmActive,false)
}

fun Context.isSleepAlarmSet():Boolean{
    return getLocalSettings().getBoolean(isSleepAlarmActive,false)
}

fun Context.isRestAlarmActive():Boolean{
    return getLocalSettings().getBoolean(isRestAlarmActive,false)
}

fun Context.isRecognitionOn():Boolean{
    return getLocalSettings().getBoolean(isActivityRecognictionOn,false)
}

fun Context.isTimerAlarmActive():Boolean{
    return getLocalSettings().getBoolean(isTimerAlarmActive,false)
}

//clean truncate these
fun Context.saveRecognitionStatus(isRecogOn:Boolean){
    val prefs = getLocalSettings()
    prefs.edit()
        .putBoolean(isActivityRecognictionOn,isRecogOn)
        .apply()
}


fun Context.saveAlarmWakeStatus(isAlarmSet:Boolean){
    val prefs = getLocalSettings()
    prefs.edit()
        .putBoolean(isWakeAlarmActive,isAlarmSet)
        .apply()
}

fun Context.saveAlarmSleepStatus(isAlarmSet:Boolean){
    val prefs = getLocalSettings()
    prefs.edit()
        .putBoolean(isSleepAlarmActive,isAlarmSet)
        .apply()
}

fun Context.saveAlarmRestStatus(isAlarmSet:Boolean){
    val prefs = getLocalSettings()
    prefs.edit()
        .putBoolean(isRestAlarmActive,isAlarmSet)
        .apply()
}

fun Context.saveAlarmTimerStatus(isAlarmSet:Boolean){
    val prefs = getLocalSettings()
    prefs.edit()
        .putBoolean(isTimerAlarmActive,isAlarmSet)
        .apply()
}



/**
 * Specify that the alarm is triggered. All preference listeners will
 * be updated
 */
fun Context.saveAlarmTimerTriggerStatus(isAlarmSet:Boolean){
    val prefs = getLocalSettings()
    prefs.edit()
        .putBoolean(isTimerAlarmTriggered,isAlarmSet)
        .apply()
}

fun Context.saveAlarmRestTriggerStatus(isAlarmSet: Boolean) {
    val prefs = getLocalSettings()
    prefs.edit()
        .putBoolean(isBreakAlarmTriggered,isAlarmSet)
        .apply()
}


/**
 * save task list
 * @param queue is the task list
 */
fun Context.saveTaskList(queue:ArrayDeque<Long>) {

    try {
        val prefs = getLocalSettings()
        val taskString = queue.joinToString(",")
        Log.d("saveTaskList", "Scheduled Tasks: $taskString")
        prefs.edit()
            .putString(TASK_INCOMPLETE, taskString).apply()

    } catch (e: Exception) {
        Log.e("saveTaskList","Could not update task list",e)
    }

}


/**
 * save completed task list
 * @param queue is the completed task list
 */
fun Context.saveCompletedTaskList(queue:ArrayDeque<Long>) {

    try {
        val prefs = getLocalSettings()
        val taskString = queue.joinToString(",")
        Log.d("saveTaskList", "Scheduled Tasks: $taskString")
        prefs.edit()
            .putString(TASK_COMPLETE, taskString).apply()
    } catch (e: Exception) {
        Log.e("saveCompletedTaskList","Could not update task list",e)
    }

}



fun Context.saveTaskLists(queue:ArrayDeque<Long>,completed:ArrayDeque<Long>){
    val prefs = getLocalSettings()
    prefs.edit().apply{

        var taskString = queue.joinToString(",")
        Log.d("saveTaskList","Scheduled Tasks: $taskString")
        putString(TASK_INCOMPLETE,taskString)

        taskString = completed.joinToString(",")
        Log.d("saveTaskList","Completed Tasks: $taskString")
        putString(TASK_COMPLETE,taskString)

        apply()
    }
}

fun Context.saveOrder(list:ArrayList<Long>){
    getLocalSettings().edit().apply{
        putString(TASK_ORDER,list.joinToString(","))
    }.apply()
}

fun Context.getOrderedListAsString():String{
    return getListAsString(TASK_ORDER)
}

fun Context.getCompletedListAsString():String{
    return getListAsString(TASK_COMPLETE)
}

fun Context.getUnCompletedListAsString():String{
    return getListAsString(TASK_INCOMPLETE)
}

fun Context.getListAsString(listString:String):String{
    return getLocalSettings().getString(listString,"-1")?:"-1"
}

public fun Context.getTaskListKey() = TASK_INCOMPLETE
fun Context.getTaskFinishedKey() = TASK_COMPLETE
fun Context.getOrderKey() = TASK_ORDER


fun Context.getCompletedTaskList():ArrayDeque<Long>{
    return fetchArrayFromPreference(TASK_COMPLETE)
}


/**
 * We get the task list, if there is any.
 */
fun Context.getDayTaskList():ArrayDeque<Long>{
    return fetchArrayFromPreference(TASK_INCOMPLETE)
}

fun Context.getSavedOrder():ArrayList<Long>{
    val prefs = getLocalSettings()
    val taskString = prefs.getString(TASK_ORDER,"-1")
    if (taskString != "-1") {
        val deque = ArrayList<Long>()
        if (taskString!!.contains(",")) {

            try {
                val sequence = taskString.split(",")
                sequence.forEach {
                    try {
                        deque.add(it.toLong())
                    } catch (e: IllegalArgumentException) {
                        Log.d("getDayTaskList", "Tried to convert numbers", e)
                    }
                }
            } catch (e: Exception) {
                Log.d("getDayTaskList","",e)
                return ArrayList()
            }
        }else{
            //only one task today?

            if (taskString.isNotEmpty()) {
                deque.add(taskString.toLong())
            }
        }
        return deque
    }else{
        return ArrayList()
    }
}


fun Context.fetchArrayFromPreference(listName:String):ArrayDeque<Long>{
    val prefs = getLocalSettings()
    val taskString = prefs.getString(listName,"-1")
    return stringToArray(taskString)
}



fun stringToArray(taskString:String?):ArrayDeque<Long>{
    if (taskString != "-1") {
        val deque = ArrayDeque<Long>()
        if (taskString!!.contains(",")) {

            try {
                val sequence = taskString.split(",")
                sequence.forEach {
                    try {
                        deque.add(it.toLong())
                    } catch (e: IllegalArgumentException) {
                        Log.d("getDayTaskList", "Tried to convert numbers", e)
                    }
                }
            } catch (e: Exception) {
                Log.d("getDayTaskList","",e)
                return ArrayDeque()
            }
        }else{
            //only one task today?

            if (taskString.isNotEmpty()) {
                deque.add(taskString.toLong())
            }
        }
        return deque
    }else if(taskString.isNullOrBlank()){
        return ArrayDeque()
    }else{
        return ArrayDeque()
    }
}


fun Context.clearSavedArrays(){
    getLocalSettings().edit()
        .putString(TASK_COMPLETE,"")
        .putString(TASK_ORDER,"")
        .putString(TASK_INCOMPLETE,"")
        .apply()
}


/**
 * If the user has approved the list and is progressing through their tasks
 * then we know the day is active
 */
fun Context.isDayActive():Boolean{
    return getLocalSettings().getBoolean(IS_ACTIVE,false)
}

fun Context.EnableScheduler(){
    getLocalSettings().edit().putBoolean(IS_ACTIVE,true).apply()
}

fun Context.DisableScheduler(){
    getLocalSettings().edit().putBoolean(IS_ACTIVE,false).apply()
}

fun Context.saveOnboardStatus(onboarding:Boolean){
    getLocalSettings().edit().putBoolean(ONBOARDING,onboarding).apply()
}

fun Context.getOnboardStatus():Boolean{
    return getLocalSettings().getBoolean(ONBOARDING, false)
}

fun Context.saveAllLists(order:String, uncompleted:String, completed:String){
    getLocalSettings().edit()
        .putString(TASK_ORDER,order)
        .putString(TASK_INCOMPLETE,uncompleted)
        .putString(TASK_COMPLETE,completed)
        .apply()
}


//SOME USER PREFERENCES
//TODO find a way to share these values across xml and across modules
fun Context.isNotificationStubborn():Boolean{
    return getLocalSettings().getBoolean(PREF_STUBBORN,false)
}

fun Context.setNotificationStubborn(boolean: Boolean){
    getLocalSettings().edit().putBoolean(PREF_STUBBORN,false).apply()
}


@TargetApi(23)
fun Context.getAllTaskShowing():Array<StatusBarNotification>{

    //TODO SPLIT
    return(getSystemService(NOTIFICATION_SERVICE) as NotificationManager).activeNotifications
//    return getNotificationManager().activeNotifications
}

fun SharedPreferences.getWakeTime(identifier: Identifier):Time{

    return Time(
        identifier,
        Hour(getInt(WAKE_UP_HOUR,-1)),
        Minute(getInt(WAKE_UP_MINUTE,-1)),
        Phase(getInt(WAKE_PHASE,0))
    )
}

fun SharedPreferences.getSleepTime(identifier: Identifier):Time{
    return Time(
        identifier,
        Hour(getInt(SLEEP_HOUR,-1)),
        Minute(getInt(SLEEP_MINUTE,-1)),
        Phase(getInt(SLEEP_PHASE,0))
    )
}
