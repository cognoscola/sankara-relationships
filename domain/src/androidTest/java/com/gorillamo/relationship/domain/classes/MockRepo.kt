package com.gorillamo.relationship.domain.classes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.scheduler.Minute
import com.gorillamo.scheduler.TimeValue

class MockRepo:RelationshipRepository{

    var insertOrUpdateRelationshipCallCount = 0

    override suspend fun deleteRelationship(name: String): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getRelationshipsLive(): LiveData<out List<Relationship>?> {
        return getMockLiveData()
    }

    override fun getTodaysRelationships(): LiveData<out List<Relationship>?> {
        return getMockLiveData()
    }

    override suspend fun insertOrUpdateRelationship(relationship: Relationship): Long {
        insertOrUpdateRelationshipCallCount++
        return 3
    }


    private fun getMockLiveData():LiveData<List<Relationship>> {
        val MOCK_LISt = generateRelationshipList()
        val mockLiveData = MutableLiveData<List<Relationship>>()
        mockLiveData.value = MOCK_LISt
        return mockLiveData
    }

    private fun generateRelationshipList():List<Relationship>{

        return List(5){

            object :Relationship{
                override val id: Int get() = it
                override val name: String get() = "name $it"
                override val lastContacted: Long get() = System.currentTimeMillis()
                override val count: Int get() = 1
                override val range: Int get() = 1
                override val ready: Boolean get() = false
            }
        }

    }
}