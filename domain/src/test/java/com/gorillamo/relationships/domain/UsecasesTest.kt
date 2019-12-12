package com.gorillamo.relationships.domain

import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationships.domain.usecase.SaveRelationshipUseCase
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
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

    @Before
    fun setUP(){

        insert = SaveRelationshipUseCase(repo)

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
}
