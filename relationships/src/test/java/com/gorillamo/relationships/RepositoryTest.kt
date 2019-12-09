package com.gorillamo.relationships

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.LiveData
import com.gorillamo.relationships.entity.RelationshipDao
import com.gorillamo.relationships.entity.RelationshipDatabaseObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest {


    @Mock
    lateinit var dao: RelationshipDao

    lateinit var cal: Calendar
    lateinit var repo: RelationshipRepository

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)



    @Before
    fun setUp(){
        cal = Calendar.getInstance()
        repo = RelationshipRepositoryImpl.getInstance(dao)


    }

    val RELATIONSHIPS = List(5){
        Relationship(
            "Person $it",
            5000 * it.toLong()
        )
    }

    inline fun <reified T> lambdaMock(): T = mock(T::class.java)

    @Test
    fun shouldReturnEmptySuccessfulResult(){

        //when we call the repo, fetch data from db and it is empty is empty, we should form a
        //Result (Successul) but which is also empty

//        val observer = lambdaMock<(String) -> Unit>()
//        val lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
//        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)


        /*val result = repo.observeAllRelationshipList()

        result.observeOnce {

            Log.d(" shouldReturnEmptySuccessfulResult","")
            verify(dao).getAllRelationship()
        }*/

//

    }

    @Test
    fun shouldFetchAllFromLocalDataStore(){




       /* `when`(dao.getAllRelationship()).thenReturn(




        )*/
        /*val RELATIONSHIPS = List(5) {

            val today = Calendar.getInstance()
            today.add(Calendar.SECOND, it)
            RelationshipDatabaseObject(
                "Name $it",
                today.timeInMillis
            )
        }

        assertEquals(RELATIONSHIPS,repo.getAllRelationships() )*/


/*




        Mockito.`when`(mockRepository.getRelationBasedOnDay(today)).thenReturn(TODAYS_RELATIONSHIPS)
        presenter.loadToday()
        Mockito.verify(mockView, Mockito.atMost(1)).displayRelationships(TODAYS_RELATIONSHIPS)
*/
    }

    @Test
    fun shouldHandleError(){

/*
        Mockito.`when`(mockRepository.getAllRelationships()).thenThrow(RuntimeException("Boom"))
        Mockito.`when`(mockRepository.getRelationBasedOnDay(cal))
            .thenThrow(RuntimeException("Boom"))

        presenter.loadToday()
        presenter.loadAll()

        Mockito.verify(mockView, Mockito.atLeast(2)).displayError()
*/
    }


}
