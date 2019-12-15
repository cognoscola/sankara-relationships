package com.gorillamo.relationship.domain.usecase

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseSync

class LoadRelationshipsUseCase (
    private val repository: RelationshipRepository
):UseCaseSync<LiveData<out List<Relationship>>>(){

    override fun buildUseCase(): LiveData<out List<Relationship>> {
        return repository.getRelationshipsLive()
    }
}