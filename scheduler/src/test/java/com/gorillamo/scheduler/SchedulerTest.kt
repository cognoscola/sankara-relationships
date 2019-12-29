package com.gorillamo.scheduler

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SchedulerTest{

    lateinit var scheduler:Scheduler<AGenericObject>

    @Before
    fun setup() {
        scheduler = DaySchedulerAdapter {

            SchedulingItem(
                item = it,
                timeLastInteracted = PointInTime(it.timeLastContacted!!),
                count = Value(it.count),
                range = Value(it.range)
            )
        }
    }


    @Test
    fun `returns no items`(){
        //Given items
        val ItemstoScheduleForToday = 0
        val total = 5
        val items = generateItems(total,ItemstoScheduleForToday)

        //when
        val outList = scheduler.getItemsDue(items)

        assert(outList.size == ItemstoScheduleForToday)
    }

    @Test
    fun `returns two items`(){
        //Given items
        val ItemstoScheduleForToday = 2
        val items = generateItems(5,ItemstoScheduleForToday)

        //when
        val outList = scheduler.getItemsDue(items)

        assert(outList.size == ItemstoScheduleForToday)
        assert(outList[0].name == "Name 0")
        assert(outList[1].name == "Name 1")
    }

    @Test
    fun `returns 5 items`(){
        //Given items
        val ItemstoScheduleForToday = 5
        val items = generateItems(5,ItemstoScheduleForToday)

        //when
        val outList = scheduler.getItemsDue(items)

        assert(outList.size == ItemstoScheduleForToday)
        assert(outList[0].name == "Name 0")
        assert(outList[1].name == "Name 1")
    }

    @Test
    fun `handles new or unknown lastTimeContacted object`(){

        val ZERO_TIME_ITEM = AGenericObject(
            name = "Name 0",
            timeLastContacted = 0,
            count = 0,
            range = 1
        )

        val NEGATIVE_TIME_ITEM  = AGenericObject(
            name = "Name 1",
            timeLastContacted = -1,
            count = 1,
            range = 1
        )

        val FUTURE_ITEM = AGenericObject(
            name = "Name 2",
            timeLastContacted = System.currentTimeMillis() + oneDayInMillis(),
            count = 1,
            range = 1
        )

        val input = listOf(ZERO_TIME_ITEM,NEGATIVE_TIME_ITEM,FUTURE_ITEM)

        val scheduled = scheduler.getItemsDue(input)

        assert(scheduled.size == 2)
        assert(scheduled[0].name == "Name 0")
        assert(scheduled[1].name == "Name 1")


    }

    companion object{

/**
         * Generates a list of SCheduling items of size @param total
         * @param total
         * @param today marks how many should be today
         */

        fun generateItems(total:Int, today:Int):List<AGenericObject>{

            var todayRemainig = today

            return List(total){

                if(todayRemainig > -1) todayRemainig--;

                    AGenericObject(
                        "Name $it",
                        System.currentTimeMillis() - if(todayRemainig>-1){ oneDayInMillis()*(it+1)} else {0},
                        1,
                        (it+1)
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