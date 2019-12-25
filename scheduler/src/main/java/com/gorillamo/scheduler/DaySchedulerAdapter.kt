package com.gorillamo.scheduler

import java.time.*

class DaySchedulerAdapter:SchedulerPort{

    private var today = LocalDate.now()
    private var time = LocalDateTime.now()
    private var dayOfLastInteraction:LocalDate? = null
//    private var timeOfLastInteraction:LocalDateTime? = null
    private var zonedDateTime:ZonedDateTime? = null
    private var dayDiff = 0
    private var outList= ArrayList<Int>()
    private var period:Period? = null

    /**
     * Get the list of today's schedule
     */
    override fun getDueItems(input: List<SchedulingItem>): List<Int> {

        outList.clear()

        input.forEach {

            zonedDateTime = Instant.ofEpochMilli(it.timeLastInteracted.get()).atZone(ZoneId.systemDefault())

            //For now we'll just go at most once per day
            if(it.frequency.get() <= 1.0f){

                dayOfLastInteraction = zonedDateTime!!.toLocalDate()

                //how many days since last interaction?
                period = Period.between(dayOfLastInteraction, today)
                val diff: Int = period!!.getDays()

                if(diff*it.frequency.get() >= 1.0f)
                    outList.add(it.id.get())
            }
        }

        return outList.toList()
    }
}