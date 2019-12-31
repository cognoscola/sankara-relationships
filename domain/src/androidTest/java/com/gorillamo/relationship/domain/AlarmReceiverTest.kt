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
import io.mockk.*
import kotlinx.coroutines.runBlocking
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
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class AlarmReceiverTest: KoinTest {

    lateinit var context: Context
    lateinit var alarmReceiver: AlarmReceiver


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
            single{ mockk<RelationshipRepository>()}
            single{ mockk<Scheduler<Relationship>>()}
        })

        context = mockk<Context>(relaxed = true)
        alarmReceiver = AlarmReceiver()
    }

    @After
    fun cleanUp() {
        stopKoin()
    }

    @Test
    fun `verify_task_were_scheduled_during_OnReceive`(){

        val intent = Intent().apply {
            putExtra(KEY_ALARM,true)
            action = EVENT_WAKEUP
        }

        val MOCK_LISt = generateRelationshipList()
        val mockLiveData = MutableLiveData<List<Relationship>>()
        mockLiveData.value = MOCK_LISt
//        return mockLiveData

        val repo:RelationshipRepository = get()
        val scheduler:Scheduler<Relationship> = get()

        every { repo.getRelationshipsLive() } returns mockLiveData
        every { scheduler.getItemsDue(any()) } returns generateReadyRelationshipList(5)

        alarmReceiver.onReceive(context,intent)

        verify {
            repo.getRelationshipsLive()
            scheduler.getItemsDue(any())
        }

//        verify(atLeast = 5) { runBlocking { repo.insertOrUpdateRelationship(any()) }  }

        confirmVerified(repo,scheduler)
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

  /*  private fun getMockLiveData():LiveData<List<Relationship>>{


    }
*/
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