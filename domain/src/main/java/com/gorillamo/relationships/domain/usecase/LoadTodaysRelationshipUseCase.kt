package com.gorillamo.relationships.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseSync

class LoadTodaysRelationshipUseCase (

    private val repository: RelationshipRepository
): UseCaseSync<LiveData<out List<Relationship>>>(){

    override fun buildUseCase(): LiveData<out List<Relationship>> {

        return repository.getTodaysRelationship()
    }
}