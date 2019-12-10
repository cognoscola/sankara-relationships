package com.gorillamo.relationships.domain.adapters

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

    override fun getRelationshipsLive(): LiveData<out List<Relationship>?>  = relationshipDaoPort.getBooksLive()
}