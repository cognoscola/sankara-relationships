package com.gorillamo.relationships.domain.usecase

import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository

class SaveRelationshipUseCase(
    private val repository: RelationshipRepository
):UseCaseWithParams<Relationship,Long>()
{
    override suspend fun buildUseCase(relation: Relationship): Long{
        return repository.insertOrUpdateRelationship(relation)
    }
}