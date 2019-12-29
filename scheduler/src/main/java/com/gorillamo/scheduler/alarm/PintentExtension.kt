package com.gorillamo.scheduler.alarm

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.gorillamo.scheduler.alarm.AlarmReceiver.Companion.ACTION_SLEEP
import com.gorillamo.scheduler.alarm.AlarmReceiver.Companion.EVENT_WAKEUP
import com.gorillamo.scheduler.alarm.AlarmReceiver.Companion.SLEEP_INTENT_CODE
import com.gorillamo.scheduler.alarm.AlarmReceiver.Companion.WAKE_UP_INTENT_CODE


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
//            0) //We don't care about extras right now
            PendingIntent.FLAG_UPDATE_CURRENT) //change if we are carrying extras
}

fun Context.createWakeUpAlarmPendingIntent():PendingIntent{

    return createAlarmPendingIntent(createAlarmIntent()
            .apply { action = EVENT_WAKEUP }, WAKE_UP_INTENT_CODE)
}


fun Context.createSleepAlarmPendingIntent():PendingIntent{
    return createAlarmPendingIntent(createAlarmIntent()
            .apply { action = ACTION_SLEEP }, SLEEP_INTENT_CODE)
}

/**
 * Create an alarm intent for AlarmReceiverClass
 * @receiver Context application context
 * @return Intent
 */
fun Context.createAlarmIntent():Intent{
    return Intent(this, AlarmReceiver::class.java).apply {
        addFlags(Intent.FLAG_RECEIVER_FOREGROUND) //to give forground priority
            putExtra(AlarmReceiver.KEY_ALARM,true)
    }
}