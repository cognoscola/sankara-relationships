package com.gorillamo.relationship.domain.usecase

import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseWithParams

class DeleteRelationshipUseCase (
    val repository: RelationshipRepository
):UseCaseWithParams<Relationship, Int>(){
    override suspend fun buildUseCase(params: Relationship):Int {

        return repository.deleteRelationship(params)
    }
}