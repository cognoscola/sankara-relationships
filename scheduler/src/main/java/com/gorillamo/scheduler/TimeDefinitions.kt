package com.gorillamo.scheduler

import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

data class PointInTime(private val time:Long){ fun get() = time }
data class Identifier(val id:Int){ fun get() =  id}
data class Frequency(val frequency:Float) { fun get() = frequency}

data class SchedulingItem<T>(

    //TODO if id is already with a number then skip it
    var item:T,
    var timeLastInteracted:PointInTime,
    var frequency: Frequency,
    var id:Identifier = Identifier(0)

)