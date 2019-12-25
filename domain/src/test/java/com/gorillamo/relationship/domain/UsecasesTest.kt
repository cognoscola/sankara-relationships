package com.gorillamo.relationship.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.usecase.DeleteRelationshipUseCase
import com.gorillamo.relationship.domain.usecase.LoadRelationshipsUseCase
import com.gorillamo.relationship.domain.usecase.LoadTodaysRelationshipUseCase
import com.gorillamo.relationship.domain.usecase.SaveRelationshipUseCase
import com.gorillamo.scheduler.SchedulerPort
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.spy
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class UsecasesTest {

    @Mock
    lateinit var repo:RelationshipRepository

    @Mock
    lateinit var schedulerPort: SchedulerPort

    lateinit var insert:SaveRelationshipUseCase
    lateinit var load:LoadRelationshipsUseCase
    lateinit var loadToday: LoadTodaysRelationshipUseCase
    lateinit var delete: DeleteRelationshipUseCase

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUP(){

        insert = SaveRelationshipUseCase(repo)
        load = LoadRelationshipsUseCase(repo)
        delete = DeleteRelationshipUseCase(repo)
        loadToday= LoadTodaysRelationshipUseCase(repo,schedulerPort)

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `add or replace a relationship`() = runBlockingTest{

        val data= object :Relationship{
            override val id: Int
                get() = 0
            override val name: String?
                get() = "Hello"
            override val timeLastContacted: Long?
                get() = System.currentTimeMillis()
            override val frequency: Float?
                get() = 1.0f
        }

        insert.execute(data)
        verify(repo).insertOrUpdateRelationship(data)

    }

    @Test
    fun `load relationships`(){

        val LIVE_DATA_MOCK = MutableLiveData<List<Relationship>>()
        val LIST_MOCK = generateRelationshipList()
        LIVE_DATA_MOCK.value = LIST_MOCK

        `when`(repo.getRelationshipsLive()).thenReturn(LIVE_DATA_MOCK)

        load.execute()
        verify(repo).getRelationshipsLive()

        /**
         * Do we really need to observe this?
         */
        LIVE_DATA_MOCK.observeOnce {
            assertEquals(LIST_MOCK,it)
        }
    }

    @Test
    fun `load todays Relationship`() = runBlockingTest{

        //GIVEN
        val LIST_MOCK = generateRelationshipList()

        val LIST_SCHEDULED_ITEMS_IDS = listOf(0,1,2,3,4)
        `when`(repo.getTodaysRelationships()).thenReturn(LIST_MOCK)
        `when`(schedulerPort.getDueItems(Mockito.anyList())).thenReturn(LIST_SCHEDULED_ITEMS_IDS)

        //WHEN
        val result = loadToday.execute()

        //THEN
        verify(repo).getTodaysRelationships()
        verify(schedulerPort).getDueItems(Mockito.anyList())

        assertEquals(5,result.size)

    }

    @Test
    fun `delete a relationship`() = runBlockingTest{

        val data= generateOneRelationship()
        delete.execute(data.name!!)
        verify(repo).deleteRelationship(data.name!!)
    }


    private fun generateOneRelationship():Relationship{

        return   object :Relationship{
            override val id: Int
                get() = 0
            override val name: String?
                get() = "Hello"
            override val timeLastContacted: Long?
                get() = System.currentTimeMillis()
            override val frequency: Float?
                get() = 1.0f
        }

    }
    private fun generateRelationshipList():List<Relationship>{

        return List(5){

            object :Relationship{
                override val id: Int
                    get() = it
                override val name: String?
                    get() = "name $it"
                override val timeLastContacted: Long?
                    get() = System.currentTimeMillis()
                override val frequency: Float?
                    get() = 1.0f
            }
        }

    }

    private fun createMockObserver(): Observer<in List<Relationship>?> = spy(Observer { })
}
