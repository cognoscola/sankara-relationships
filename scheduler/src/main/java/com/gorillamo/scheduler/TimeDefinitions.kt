package com.gorillamo.scheduler

import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

data class PointInTime(private val time:Long){ fun get() = time }
data class Identifier(val id:Int){ fun get() =  id}
data class Frequency(val frequency:Float) { fun get() = frequency}

data class SchedulingItem(

    val id:Identifier,
    val timeLastInteracted:PointInTime,
    val frequency: Frequency

)