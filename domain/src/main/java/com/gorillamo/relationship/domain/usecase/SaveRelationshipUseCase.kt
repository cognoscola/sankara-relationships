package com.gorillamo.relationship.domain.usecase

import com.gorillamo.relationship.domain.dto.Relationship
import com.gorillamo.relationship.domain.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.extPorts.UseCaseWithParams

class SaveRelationshipUseCase(
    private val repository: RelationshipRepository
): UseCaseWithParams<Relationship, Long>()
{
    override suspend fun buildUseCase(params: Relationship): Long{
        return repository.insertOrUpdateRelationship(params)
    }
}