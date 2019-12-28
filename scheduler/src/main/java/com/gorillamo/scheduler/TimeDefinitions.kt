package com.gorillamo.scheduler

data class PointInTime(private val time:Long){ fun get() = time }
data class Identifier(val id:Int){ fun get() =  id}
data class Value(val value:Int) { fun get() = value}

data class SchedulingItem<T>(

    //TODO if id is already with a number then skip it
    var item:T,
    var timeLastInteracted:PointInTime,
    var count: Value,
    var range : Value,
    var id:Identifier = Identifier(0)

){


    fun getFrequency():Float{
        return count.value.toFloat().div(range.value.toFloat())
    }

}