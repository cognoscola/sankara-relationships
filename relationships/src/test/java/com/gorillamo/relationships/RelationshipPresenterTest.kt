package com.gorillamo.relationships

import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

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

    lateinit var presenter: RelationshipPresenter

    @Before
    fun setUp(){
        presenter = RelationshipPresenter(mockView,mockRepository)
    }

    val RELATIONSHIPS = List(5){
        Relationship(
            "Person $it",
            5000*it.toLong()
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


        `when`(mockRepository.getAllRelationships()).thenReturn(RELATIONSHIPS)

        presenter.loadToday()
        verify(mockView, atMost(1)).displayRelationships(RELATIONSHIPS)
    }










}
