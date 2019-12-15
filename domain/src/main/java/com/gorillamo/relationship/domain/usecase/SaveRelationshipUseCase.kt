package com.gorillamo.relationship.domain.usecase

import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseWithParams

class SaveRelationshipUseCase(
    private val repository: RelationshipRepository
): UseCaseWithParams<Relationship, Long>()
{
    override suspend fun buildUseCase(relation: Relationship): Long{
        return repository.insertOrUpdateRelationship(relation)
    }
}