package com.gorillamo.scheduler.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.util.*

/**
 * What to do when our app sounds the "wake up" alarm.
 * Again, this alarm is not a "wake from human sleep" alarm. Instead
 * it lets the user know, via some UI, notifications probably, that they
 * should prepare the day's tasks.
 *
 * There are two types of "Wake up" Alarms:
 * ACTION_ONBOARD - fires once when we are onboarding the user
 * ACTION_WAKEUP - fires every morning at the specified time
 *
 */
class AlarmReceiver:BroadcastReceiver(){

    @Suppress("unused")
    private val tag:String = AlarmReceiver::class.java.name
    companion object {

        /**
         * When this receiver has an intent with a type ACTION_ONBOARD
         * it means that it should execute in a manner in line with on-boarding
         * the user. That is, generate a notification with the user's first task
         */
        const val ACTION_ONBOARD = "com.gorillamoa.routines.event.onboard"

        /**
         * When the receiver has an intent with a type EVENT_WAKEUP, it
         * means that the receiver should process the intent normally.
         * I.e. schedule tasks as normal
         */
        const val EVENT_WAKEUP  = "com.gorillamoa.routines.event.wakeup"

        const val ACTION_SLEEP = "com.gorillamoa.routines.event.sleep"

        /**
         * Rest from whatever activity the user is curerntly undertaking
         */
        const val ACTION_REST = "R"

        const val ACTION_TIMER = "T"

        const val KEY_ALARM = "A"

        const val WAKE_UP_INTENT_CODE = 1
        const val SLEEP_INTENT_CODE =2

        private var callback:AlarmReceiverApi? = null

        fun setAlarmEventCallbacks(callbacks:AlarmReceiverApi){
            this.callback = callbacks
        }
    }

    override fun onReceive(context: Context, intent: Intent?) {
        Log.d("$tag onReceive","${intent?.action}")
        
        intent?.let {

            if (intent.hasExtra(KEY_ALARM)) {
                Log.d("onReceive","By Alarm")
            }

            when (it.action) {
                EVENT_WAKEUP ->{

                    //When you get back...
                    //Specify a bunch of functions to run when this alarm sounds.. BUT
                    //the functions need to be specified in the other library...


                }
            }


/*
            when (it.action) {

                ACTION_ONBOARD ->{


                }

                EVENT_WAKEUP -> {

                    //TODO CHECK IF WE HAVEN"T ALREADY RECEIVED THIS EVENT. USE THE DATA LAYER
                    // we don't want to receive two wake up events from both the Alarm and the
                    //event from the network..in which case we should just use the data layer
                    //to manage the synchronization task...

                    Toast.makeText(context,"WAkEY WAKEY",Toast.LENGTH_SHORT).show()
                    Log.d("WAKEY","WAKEY!")

                    */
/**
                     * WAKE UP FROM ALARM -> SCHEDULE TASKS -> SEND TASKS OVER
                     *//*

                    TaskScheduler.schedule(context){ tasks ->
                        tasks?.let{
                            //TODO SPLIT

                            if(callback == null){
                            Log.d(tag, "WAKE UP: Null callback")
                        }

                            callback?.processWakeUpEvent(context,tasks)?: run {

                                Log.d(tag,"WAKE UP: Null callback")

                                val one = 1
                            }
                            //We need to send this data over to mobile, mobile will do something with it.
                            //context.sendDataToMobile

//                            context.notificationShowWakeUpMirror(tasks!!)
                        }
                    }
                }

                ACTION_SLEEP ->{

                    Log.d("onReceive","Sleep Alarm went off!")
                    //TODO dismiss other task notifications

                    //TODO SPLIT
//                    context.notificationShowSleep()
                    TaskScheduler.endDay(context)

                    callback?.processSleepEvent(context)
                }

                ACTION_REST -> {


                    Log.d("$tag onReceive","Rest Timer Went off")

                    //TODO ususally if watchface is visible, we'll not show the notification, create
                    //a settings option for  this
//                    context.notificationShowRest()

                    context.saveAlarmRestTriggerStatus(true)
                }

                ACTION_TIMER ->{

                    //show a notification for the timer

                    //TODO Give option to chose this or that, but not both!
                    //TODO ususally if watchface is visible, we'll not show the notification
//                    context.notificationShowTimer()

                    //trigger any other listeners
                    context.saveAlarmTimerTriggerStatus(true)
                }


                else ->{

                    Log.e("onReceive","Alarm Intent did not have ACTION")
                    //TODO create a notification that something went wrong
                }
            }
*/
        }
    }

    interface AlarmReceiverApi{

        fun processWakeUpEvent(context: Context, tasks:ArrayDeque<Long>)

        fun processSleepEvent(context: Context)
    }
}