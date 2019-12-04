package com.gorillamo.relationships

import com.gorillamo.relationships.model.Relationship
import com.gorillamo.relationships.model.RelationshipRepository
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class RelationshipPresenterTest {

    @Mock
    lateinit var mockView: SearchRelationshipsContract.RelationshipView

    @Mock
    lateinit var mockRepository: RelationshipRepository

    lateinit var cal:Calendar
    lateinit var presenter: RelationshipPresenter

    @Before
    fun setUp(){
        cal = Calendar.getInstance()
        presenter = RelationshipPresenter(mockView,mockRepository,cal)
    }

    val RELATIONSHIPS = List(5){
        Relationship(
            "Person $it",
            5000 * it.toLong()
        )
    }

    @Test
    fun shouldFetchAll(){

        `when`(mockRepository.getAllRelationships()).thenReturn(RELATIONSHIPS)
        presenter.loadAll()
        verify(mockView, atMost(1)).displayRelationships(RELATIONSHIPS)
    }

    @Test
    fun shouldFetchToday(){

        val today = Calendar.getInstance()
        val TODAYS_RELATIONSHIPS = List(5) {

            today.add(Calendar.SECOND, it)
            Relationship(
                "Name $it",
                today.timeInMillis
            )
        }


        `when`(mockRepository.getRelationBasedOnDay(today)).thenReturn(TODAYS_RELATIONSHIPS)
        presenter.loadToday()
        verify(mockView, atMost(1)).displayRelationships(TODAYS_RELATIONSHIPS)
    }

    @Test
    fun shouldHandleError(){

        `when`(mockRepository.getAllRelationships()).thenThrow(RuntimeException("Boom"))
        `when`(mockRepository.getRelationBasedOnDay(cal)).thenThrow(RuntimeException("Boom"))

        presenter.loadToday()
        presenter.loadAll()

        verify(mockView, atLeast(2)).displayError()
    }










}
