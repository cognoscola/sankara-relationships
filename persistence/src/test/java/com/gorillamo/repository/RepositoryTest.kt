package com.gorillamo.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.repository.entity.RelationshipDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
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
//        repo = RelationshipRepositoryImpl.getInstance(dao)
    }

    inline fun <reified T> lambdaMock(): T = mock(T::class.java)






}
