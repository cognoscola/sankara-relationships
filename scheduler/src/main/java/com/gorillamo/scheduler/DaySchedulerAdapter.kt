package com.gorillamo.scheduler

import android.app.AlarmManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.gorillamo.scheduler.alarm.*
import org.threeten.bp.*


import kotlin.collections.ArrayList

/**
 * Will schedule some generic items
 */
private inline fun <T> T.then(name: T.() -> T): T {

    //Defining any generic objet T,
    //we can call the function then on object T
    //The first parameter of then() is T's lambda function ()->T, which returns back the generic object T
    //We return the result of the lamda function ()->T, because we specified then to return T
    return name()
}

private inline fun <T,R> T.finish(name: T.()->R):R{

    return name()
}

private inline fun <T,R> T.first(generic: T.()->R): R {
    return generic()
}


internal class DaySchedulerAdapter<T> (val convert:(T)->SchedulingItem<T>):Scheduler<T> {

    private var today = LocalDate.now()
    private var time = LocalDateTime.now()
    private var dayOfLastInteraction: LocalDate? = null
    private var zonedDateTime: ZonedDateTime? = null
    private var dayDiff = 0
    private var outList = ArrayList<SchedulingItem<T>>()
    private var period: Period? = null

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

        const val EVENT_SLEEP = "com.gorillamoa.routines.event.sleep"

        /**
         * Rest from whatever activity the user is curerntly undertaking
         */
        const val ACTION_REST = "R"

        const val ACTION_TIMER = "T"

        const val KEY_ALARM = "A"

        const val WAKE_UP_INTENT_CODE = 1
        const val SLEEP_INTENT_CODE =2
        
    }


    /**
     * Takes a list of genetic items and must return a new list
     * of the same items, but with the condition that they are due
     */
     override fun getItemsDue(input: List<T>):List<T> {

        //because we don't know the attributes of T, we need to provide a method outside
        //of this class to convert our generic item into a Schedulable

        return input.first{convertToSchedulables(input)}
            .then { attachIdentifier(this)}
            .then { filterByItemDue(this)}
            .finish { convertToGeneric(this) }

    }

    private fun  attachIdentifier(input:List<SchedulingItem<T>>):List<SchedulingItem<T>>{
        return input.mapIndexed{ index, t -> t.apply { t.id = Identifier(index) }}
    }

    private fun convertToSchedulables(input: List<T>):List<SchedulingItem<T>> {
        return input.map { convert(it) }
    }

    private fun convertToGeneric(input:List<SchedulingItem<T>>):List<T>{
        return input.map { it.item }
    }


    /**
     * Get the list of today's schedule
     */
    private fun filterByItemDue(input: List<SchedulingItem<T>>): List<SchedulingItem<T>> {

        outList.clear()

        input.forEach {

            if (it.timeLastInteracted.get() <= 0) {
                outList.add(it)
            } else if (it.timeLastInteracted.get() > System.currentTimeMillis()) {

                //Do nothing
            } else {

                zonedDateTime =
                    Instant.ofEpochMilli(it.timeLastInteracted.get()).atZone(ZoneId.systemDefault())

                //For now we'll just go at most once per day
                if (it.getFrequency() <= 1.0f) {

                    dayOfLastInteraction = zonedDateTime!!.toLocalDate()

                    //how many days since last interaction?
                    period = Period.between(dayOfLastInteraction, today)
                    val diff: Int = period!!.getDays()

                    if (diff * it.getFrequency() >= 1.0f)
                        outList.add(it)
                }
            }
        }

        return outList.toList()
    }

    /**
     * Start a set of repeating alarms with the Specified Time Objects
     */
    override fun startScheduling(context: Context,  tasks:List<Task> ) {

        tasks.forEachIndexed() { index, task ->

            with(context) {

                saveWakeTime(task.time)
                alarmEnableWakeUpPersistent(task)
            }
        }
    }

    /**
     * Stop a set of repeating alarms with the set of Identifiers
     */
    override fun stopScheduling(context: Context, identifier: Identifier) {

        with(context) {
            alarmDisableWakePersistent()
        }
    }

}
