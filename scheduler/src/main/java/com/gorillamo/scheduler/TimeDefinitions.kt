package com.gorillamo.scheduler

import com.gorillamo.scheduler.alarm.setTimeToCalendar
import java.util.*


data class PointInTime(private val time:Long){ fun get() = time }

data class Identifier(val id:Int){ fun get() =  id}

data class Value(val value:Int) { fun get() = value}

open class TimeValue(private val value:Int) {
    fun get() = value}

sealed class Phase(){

    object AM:Phase()
    object PM:Phase()

    fun toInt():Int{
        return when (this) {
            Phase.AM -> 0
            Phase.PM -> 1
        }
    }

    companion object{
        operator fun invoke(phase:Int = 0):Phase =
            if((phase <0).or(phase < 1)) throw IllegalArgumentException("Value must be 0 or 1") else { if(phase == 0) AM else PM}


    }
}


/**
 * Takes in a value 0 <= x <= 60
 */
class Minute private constructor(minute:Int):TimeValue(minute){
    companion object {
        operator fun invoke(minute:Int=0):Minute =
            if((minute > 60).or(minute<0)) throw IllegalArgumentException("Value must be between 0 and 60") else Minute(minute)
    }
}

/**
 * a value 0 <= x <= 24
 */
class Hour private constructor(hour:Int):TimeValue(hour){
    companion object {
        operator fun invoke(hour:Int = 0):Hour =
            if((hour > 24).or(hour<0)) throw IllegalArgumentException("Value must be between 0 and 12") else Hour(hour)
    }
}

/**
 * A time object that containts
 * @param Hour which indicates the hour,
 * @param Minute which indicates the minute,
 */
data class Time(
    val identifier: Identifier,
    val hour:Hour,
    val minute:Minute,
    val phase:Phase
){
    //TODO make sure it is in the future! AKA, if time is specified before now, then increment day
    fun toCalendar() = Calendar.getInstance().apply { setTimeToCalendar(this@Time,0) }

}

class Task private constructor(val id:Identifier) {
     lateinit var time: Time
     lateinit var taskClass: Class<*>

    fun at(time: Time):Task{
        this.time = time
        return this
    }
    fun run(taskClass: Class<*>):Task{
        this.taskClass = taskClass
        return this
    }

    companion object{
         fun newTask(id:Identifier) = Task(id)
    }
}




data class SchedulingItem<T>(

    //TODO if id is already with a number then skip it
    var item:T,
    var timeLastInteracted:PointInTime,
    var count: Value,
    var range : Value,
    var id:Identifier = Identifier(0)

){
    fun getFrequency():Float{
        Calendar.AM
        return count.value.toFloat().div(range.value.toFloat())
    }

}