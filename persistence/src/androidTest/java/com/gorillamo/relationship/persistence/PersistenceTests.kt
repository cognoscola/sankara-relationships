package com.gorillamo.relationship.persistence

import android.content.Context
import androidx.annotation.Nullable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.gorillamo.relationship.persistence.entity.ApplicationDatabase
import com.gorillamo.relationship.persistence.entity.BaseDao
import com.gorillamo.relationship.persistence.entity.RelationshipDao
import com.gorillamo.relationship.persistence.entity.RelationshipDatabaseObject
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.lang.RuntimeException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class PersistenceTests {

    private lateinit var dao: RelationshipDao
    private lateinit var db: ApplicationDatabase

    @Before
    fun setUp(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ApplicationDatabase::class.java).build()
        dao = db.relationshipDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    @Throws(Exception::class)
    fun writeRelationShipAndReadList() = runBlocking {
        val relationShip = generateDataBaseObjectList(1)
        (dao as BaseDao<RelationshipDatabaseObject>).insertOrReplace(relationShip[0])

        val liveData = dao.getAllRelationship()

        val value = getValue(liveData)

        MatcherAssert.assertThat(value!!.size , CoreMatchers.equalTo(1))
        MatcherAssert.assertThat(value.get(0), CoreMatchers.equalTo(relationShip[0]))
    }

    @Test
    @Throws(Exception::class)
    fun fetchAllTest() = runBlocking {
        val relationships = generateDataBaseObjectList(1)

        relationships.forEach {
            (dao as BaseDao<RelationshipDatabaseObject>).insertOrReplace(it)
        }

        val liveData = dao.getAllRelationship()
        val value = getValue(liveData)

        MatcherAssert.assertThat(value!!.size , CoreMatchers.equalTo(relationships.size))

        relationships.forEachIndexed { index, item ->
            MatcherAssert.assertThat(value.get(index), CoreMatchers.equalTo(item))
        }
    }

    @Test
    @Throws(Exception::class)
    fun fetchReadyTest() = runBlocking {
        //given
        val relationships = generateDataBaseObjectList(5,2)
        relationships.forEach {
            (dao as BaseDao<RelationshipDatabaseObject>).insertOrReplace(it)
        }

        //Then
        val liveData = dao.getTodaysRelationship()


        val value = getValue(liveData)
        MatcherAssert.assertThat(value!!.size , CoreMatchers.equalTo(2))
        for(i in 0..1) {
            MatcherAssert.assertThat(value.get(i), CoreMatchers.equalTo(relationships[i]))
        }
    }

    private fun generateDataBaseObjectList(size:Int): List<RelationshipDatabaseObject> {

        val items = List<RelationshipDatabaseObject>(size){
            RelationshipDatabaseObject(
                id = it+1,
                name = "George",
                lastContacted = System.currentTimeMillis() - 86400000,
                frequency = 1.0f
            )
        }
        return items
    }

    private fun generateDataBaseObjectList(size:Int,readyCount:Int): List<RelationshipDatabaseObject> {

        if(readyCount > size) throw RuntimeException("ready count must be <= than size")

        var remaining = readyCount

        val items = List<RelationshipDatabaseObject>(size){

            remaining--
            RelationshipDatabaseObject(
                id = it+1,
                name = "George",
                lastContacted = System.currentTimeMillis() - 86400000,
                frequency = 1.0f,
                ready = remaining >= 0
            )
        }
        return items
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
