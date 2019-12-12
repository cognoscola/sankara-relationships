package com.gorillamo.relationships.domain.adapters

import android.util.Log
import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationships.domain.ports.RelationshipDaoPort

/**
 * This is an implementation of the repository object
 */
internal class RelationshipRepositoryAdapter(
    private val relationshipDaoPort: RelationshipDaoPort
):RelationshipRepository
{

    @Suppress("unused")
    private val tag:String = RelationshipRepositoryAdapter::class.java.name

    override fun getRelationshipsLive(): LiveData<out List<Relationship>?>  = relationshipDaoPort.getBooksLive()

    override suspend fun insertOrUpdateRelationship(relationship: Relationship):Long{
        return relationshipDaoPort.insertOrUpdate(relationship)
    }

}