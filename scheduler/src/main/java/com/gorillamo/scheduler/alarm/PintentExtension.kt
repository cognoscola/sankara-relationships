package com.gorillamo.scheduler.alarm

import android.app.PendingIntent
import android.content.Context
import android.content.Intent


public const val TASK_ID ="TaskId"
/**
 * creates an intent to access the NotificationActionReceiver's actions
 * @receiver Context
 * @param action String
 * @return PendingIntent
 */
fun Context.createAlarmPendingIntent(intent:Intent, code:Int):PendingIntent{
    return PendingIntent.getBroadcast(this,
            code,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT) //change if we are carrying extras
}

/**
 * Check if an alarm exists
 * @receiver Context application context
 * @return Intent
 */
fun Context.createAlarmExistentPendingIntent(intent: Intent,code: Int):PendingIntent?{
    return PendingIntent.getBroadcast(this,
        code,
        intent,
        PendingIntent.FLAG_NO_CREATE)
}


/**
 * Ation is  One of EVENT_WAKEUP, ACTION_SLEEP
 */
//TODO save this for something else
fun Context.createAlarmIntent(cls:Class<*>, action: String):Intent{
    return Intent(this, cls).apply {
        addFlags(Intent.FLAG_RECEIVER_FOREGROUND) //to give forground priority
//        putExtra(KEY_ALARM, true)
        this.action = action
    }
}