package com.gorillamo.relationship.domain.usecase

import com.gorillamo.relationship.domain.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.extPorts.UseCaseWithParams

class DeleteRelationshipUseCase (
    val repository: RelationshipRepository
):UseCaseWithParams<String, Int>(){
    override suspend fun buildUseCase(params: String):Int {

        return repository.deleteRelationship(params)
    }
}