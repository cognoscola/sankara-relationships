package com.gorillamo.relationship

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.work.ListenableWorker.*
import androidx.work.testing.TestListenableWorkerBuilder

import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`

import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.Executor

@RunWith(AndroidJUnit4::class)
class RelationshipSchedulerTest {

    private lateinit var context: Context

    @Before
    fun setUp() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun testSleepWorker() {
        // Kotlin code can use the TestListenableWorkerBuilder extension to
        // build the ListenableWorker
        val worker = TestListenableWorkerBuilder<RelationshipScheduler>(context).build()
        runBlocking {
            val result = worker.doWork()
            assertThat(result, `is`(Result.success()))

        }
    }
}