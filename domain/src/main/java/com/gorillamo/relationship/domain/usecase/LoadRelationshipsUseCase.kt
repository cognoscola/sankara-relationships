package com.gorillamo.relationship.domain.usecase

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.domain.dto.Relationship
import com.gorillamo.relationship.domain.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.extPorts.UseCaseSync

class LoadRelationshipsUseCase (
    private val repository: RelationshipRepository
):UseCaseSync<LiveData<out List<Relationship>>>(){

    override fun buildUseCase(): LiveData<out List<Relationship>> {
        return repository.getRelationshipsLive()
    }
}