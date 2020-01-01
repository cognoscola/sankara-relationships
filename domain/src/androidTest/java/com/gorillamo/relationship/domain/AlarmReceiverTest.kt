package com.gorillamo.relationship.domain

import android.content.Context
import android.content.Intent
import androidx.annotation.Nullable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.receivers.AlarmReceiver
import com.gorillamo.relationship.domain.receivers.AlarmReceiver.Companion.EVENT_WAKEUP
import com.gorillamo.relationship.domain.receivers.AlarmReceiver.Companion.KEY_ALARM
import com.gorillamo.scheduler.Scheduler
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AlarmReceiverTest: KoinTest {

    lateinit var context: Context
    lateinit var alarmReceiver: AlarmReceiver

    val dispatcher = Dispatchers.Unconfined


    /**
     * We need this executor rule here because the LiveData Object requires it
     */
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
//        context = ApplicationProvider.getApplicationContext<Context>()

        loadKoinModules(module{
            single{ Mockito.mock(RelationshipRepository::class.java) }
            single{ Mockito.mock(Scheduler::class.java)}
            single{ Dispatchers.Unconfined }
        })

        context = Mockito.mock(Context::class.java)
        alarmReceiver = AlarmReceiver()
    }

    @After
    fun cleanUp() {
        stopKoin()
    }


    @Test
    fun `verify_task_were_scheduled_during_OnReceive`()= runBlocking {

        val intent = Intent().apply {
            putExtra(KEY_ALARM,true)
            action = EVENT_WAKEUP
        }
        val repo:RelationshipRepository = get()
        val scheduler:Scheduler<Relationship> = get()

        `when`(repo.getRelationshipsLive()).thenReturn(getMockLiveData())
        `when`(scheduler.getItemsDue(ArgumentMatchers.any())).thenReturn( generateReadyRelationshipList(5))
        `when`(repo.insertOrUpdateRelationship(ArgumentMatchers.any())).thenReturn(3)

        alarmReceiver.onReceive(context,intent)

        verify {
            repo.getRelationshipsLive()
            scheduler.getItemsDue(any())
        }
        verify(repo, atLeast(5)).insertOrUpdateRelationship(ArgumentMatchers.any())


    }

     private fun getMockLiveData():LiveData<List<Relationship>> {
         val MOCK_LISt = generateRelationshipList()
         val mockLiveData = MutableLiveData<List<Relationship>>()
         mockLiveData.value = MOCK_LISt
        return mockLiveData
     }

    private fun generateRelationshipList():List<Relationship>{

        return List(5){

            object :Relationship{
                override val id: Int get() = it
                override val name: String get() = "name $it"
                override val lastContacted: Long get() = System.currentTimeMillis()
                override val count: Int get() = 1
                override val range: Int get() = 1
                override val ready: Boolean get() = false
            }
        }

    }

    @Throws(InterruptedException::class)
    fun <T> getValue(liveData: LiveData<T>): T? {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer: Observer<T> = object : Observer<T> {
            override fun onChanged(@Nullable o: T) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await(2, TimeUnit.SECONDS)

        @Suppress("UNCHECKED_CAST")
        return data[0] as T?
    }

    private fun generateReadyRelationshipList(count:Int):List<Relationship>{

        return List(count){

            object :Relationship{
                override val id: Int get() = it
                override val name: String get() = "name $it"
                override val lastContacted: Long get() = System.currentTimeMillis()
                override val count: Int get() = 1
                override val range: Int get() = 1
                override val ready: Boolean get() = true
            }
        }

    }

    private fun prepareSchedulingResponse(){

    }
}