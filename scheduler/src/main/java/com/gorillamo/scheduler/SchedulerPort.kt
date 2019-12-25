package com.gorillamo.scheduler

interface SchedulerPort {

    fun getDueItems(input:List<SchedulingItem>):List<Int>

    companion object{

        fun toSchedulingItems(id:Int, frequency:Float, timeLastInteracted:Long):SchedulingItem{
            return SchedulingItem(Identifier(id), PointInTime(timeLastInteracted),
                Frequency(frequency)
            )
        }

    }
}