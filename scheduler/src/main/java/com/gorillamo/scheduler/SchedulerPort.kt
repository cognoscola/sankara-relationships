package com.gorillamo.scheduler

interface SchedulerPort {

    fun getItemsToday(input:List<SchedulingItem>):List<SchedulingItem>

    companion object{

        fun toSchedulingItems(id:Int, frequency:Float, timeLastInteracted:Long):SchedulingItem{
            return SchedulingItem(Identifier(id), PointInTime(timeLastInteracted),Frequency(frequency))
        }

    }
}