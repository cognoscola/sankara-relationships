package com.gorillamo.scheduler.receiver

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import com.gorillamo.scheduler.alarm.*

class SimpleBootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            // Set the alarm here.
            context.apply {
                if (isSleepAlarmSet()) {
                    alarmEnableSleep(getSavedSleepTime())
                }

                if (isWakeAlarmSet()) {
                    alarmEnableWakeUp(getSavedWakeUpTime())
                }
            }
        }
    }

    companion object {

        fun enableBootReceiver(context: Context){
            val receiver = ComponentName(context, SimpleBootReceiver::class.java)

            context.packageManager.setComponentEnabledSetting(
                    receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                    PackageManager.DONT_KILL_APP
            )
        }

        fun disableBootReciver(context: Context){
            val receiver = ComponentName(context, SimpleBootReceiver::class.java)

            context.packageManager.setComponentEnabledSetting(
                    receiver,
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                    PackageManager.DONT_KILL_APP
            )
        }
    }
}