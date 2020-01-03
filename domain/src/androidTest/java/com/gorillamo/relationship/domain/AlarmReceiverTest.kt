package com.gorillamo.relationship.domain

import android.content.Context
import android.content.Intent
import android.test.mock.MockContext
import androidx.annotation.Nullable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.classes.MockRepo
import com.gorillamo.relationship.domain.classes.MockScheduler
import com.gorillamo.relationship.domain.receivers.AlarmReceiver
import com.gorillamo.relationship.domain.receivers.AlarmReceiver.Companion.EVENT_WAKEUP
import com.gorillamo.relationship.domain.receivers.AlarmReceiver.Companion.KEY_ALARM
import com.gorillamo.scheduler.Scheduler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.get
import org.koin.dsl.module
import org.koin.test.KoinTest
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

        loadKoinModules(module{
            single<RelationshipRepository>{ MockRepo() }
            single<Scheduler<Relationship>>{ MockScheduler()}
            single{ Dispatchers.Unconfined }
        })

        context = MockContext()
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

        alarmReceiver.onReceive(context,intent)

        //check starting state is sane
        //require arguments are sane
        //assert that results are sane

        assert((repo as MockRepo).insertOrUpdateRelationshipCallCount == 5)
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



}