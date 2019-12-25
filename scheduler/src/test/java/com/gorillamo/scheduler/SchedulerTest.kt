package com.gorillamo.scheduler

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SchedulerTest{

    lateinit var scheduler:SchedulerPort

    @Before
    fun setup(){
        scheduler = DaySchedulerAdapter()
    }

    @Test
    fun `returns no items`(){
        //Given items
        val ItemstoScheduleForToday = 0
        val items = generateItems(5,ItemstoScheduleForToday)

        //when
        val outList = scheduler.getDueItems(items)

        assert(outList.size == ItemstoScheduleForToday)
    }

    @Test
    fun `returns two items`(){
        //Given items
        val ItemstoScheduleForToday = 2
        val items = generateItems(5,ItemstoScheduleForToday)

        //when
        val outList = scheduler.getDueItems(items)

        assert(outList.size == ItemstoScheduleForToday)
        assert(outList[0] == 0)
        assert(outList[1] == 1)
    }

    @Test
    fun `returns 5 items`(){
        //Given items
        val ItemstoScheduleForToday = 5
        val items = generateItems(5,ItemstoScheduleForToday)

        //when
        val outList = scheduler.getDueItems(items)

        assert(outList.size == ItemstoScheduleForToday)
        assert(outList[0] == 0)
        assert(outList[1] == 1)
    }

    @Test
    fun `handles new or unknown lastTimeContacted object`(){

        val ZERO_TIME_ITEM = SchedulingItem(
            id =Identifier(0),
            timeLastInteracted = PointInTime(0),
            frequency = Frequency(1.0f)
        )

        val NEGATIVE_TIME_ITEM  = SchedulingItem(
            id =Identifier(1),
            timeLastInteracted = PointInTime(-30),
            frequency = Frequency(1.0f)
        )

        val FUTURE_ITEM = SchedulingItem(
            id =Identifier(2),
            timeLastInteracted = PointInTime(System.currentTimeMillis() + oneDayInMillis()),
            frequency = Frequency(1.0f)
        )

        val input = listOf(ZERO_TIME_ITEM,NEGATIVE_TIME_ITEM,FUTURE_ITEM)

        val scheduled = scheduler.getDueItems(input)

        assert(scheduled.size == 2)
        assert(scheduled[0] == 0)
        assert(scheduled[1] == 1)


    }

    companion object{

        /**
         * Generates a list of SCheduling items of size @param total
         * @param total
         * @param today marks how many should be today
         */
        fun generateItems(total:Int, today:Int):List<SchedulingItem>{

            var todayRemainig = today

            return List(total){

                if(todayRemainig > -1) todayRemainig--;

                SchedulingItem(
                    Identifier(it),
                    PointInTime(System.currentTimeMillis() - if(todayRemainig>-1){ oneDayInMillis()*(it+1)} else {0}),
                    Frequency(1.0f/(it+1))
                )
            }

        }


        private fun today() = System.currentTimeMillis()

        private fun days(day:Int) = oneDayInMillis() * day

        private fun oneDayInMillis():Long{
            return 1000 * 60 * 60 * 24
        }


    }



}