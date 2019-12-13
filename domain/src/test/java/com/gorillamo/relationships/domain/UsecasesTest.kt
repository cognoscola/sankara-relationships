package com.gorillamo.relationships.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationships.domain.usecase.DeleteRelationshipUseCase
import com.gorillamo.relationships.domain.usecase.LoadRelationshipsUseCase
import com.gorillamo.relationships.domain.usecase.LoadTodaysRelationshipUseCase
import com.gorillamo.relationships.domain.usecase.SaveRelationshipUseCase
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
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
        loadToday= LoadTodaysRelationshipUseCase(repo)

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun `add or replace a relationship`() = runBlockingTest{

        val data= object :Relationship{
            override val name: String?
                get() = "Hello"
            override val timeLastContacted: Long?
                get() = System.currentTimeMillis()
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
    fun `load todays Relationship`(){

        //GIVEN
        val LIVE_DATA_MOCK = MutableLiveData<List<Relationship>>()
        val LIST_MOCK = generateRelationshipList()
        LIVE_DATA_MOCK.value = LIST_MOCK
        `when`(repo.getRelationshipsLive()).thenReturn(LIVE_DATA_MOCK)

        //WHEN
        loadToday.execute()

        //THEN
        verify(repo).getTodaysRelationship()
        LIVE_DATA_MOCK.observeOnce {
            assertEquals(LIST_MOCK,it)
        }
    }

    @Test
    fun `delete a relationship`() = runBlockingTest{

        val data= generateOneRelationship()
        delete.execute(data)
        verify(repo).deleteRelationship(data)
    }


    private fun generateOneRelationship():Relationship{

        return   object :Relationship{
            override val name: String?
                get() = "Hello"
            override val timeLastContacted: Long?
                get() = System.currentTimeMillis()
        }

    }
    private fun generateRelationshipList():List<Relationship>{

        return List(5){

            object :Relationship{
                override val name: String?
                    get() = "name $it"
                override val timeLastContacted: Long?
                    get() = System.currentTimeMillis()
            }
        }

    }

    private fun createMockObserver(): Observer<in List<Relationship>?> = spy(Observer { })
}
