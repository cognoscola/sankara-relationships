package com.gorillamo.relationship.domain.adapters

import androidx.lifecycle.LiveData
import com.gorillamo.details.Detail
import com.gorillamo.details.DetailsRepository
import com.gorillamo.relationship.domain.dto.Relationship
import com.gorillamo.relationship.domain.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.ports.RelationshipDaoPort

/**
 * This is an implementation of the repository object
 */
internal class RelationshipRepositoryAdapter(
    private val relationshipDaoPort: RelationshipDaoPort
):RelationshipRepository
{

    @Suppress("unused")
    private val tag:String = RelationshipRepositoryAdapter::class.java.name

    override fun getRelationshipsLive(): LiveData<out List<Relationship>?>  = relationshipDaoPort.getRelationshipsLive()

    override suspend fun insertOrUpdateRelationship(relationship: Relationship):Long{

        return relationshipDaoPort.insertOrUpdate(relationship)

    }

    override suspend fun deleteRelationship(name: String): Int {
        return relationshipDaoPort.deleteRelationship(name)
    }

    override fun getTodaysRelationships(): LiveData<out List<Relationship>?> {
        return relationshipDaoPort.getTodaysRelationshipLive()
    }


    override suspend fun getRelationshipAsync(): List<Relationship> {
        return relationshipDaoPort.getRelationshipAsync()
    }


}