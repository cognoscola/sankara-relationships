package com.gorillamo.relationship.domain.usecase

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCase
import com.gorillamo.relationship.abstraction.extPorts.UseCaseSync
import com.gorillamo.scheduler.SchedulerPort

class LoadTodaysRelationshipUseCase (

    private val repository: RelationshipRepository,
    private val schedulerPort: SchedulerPort

): UseCase<List<Int>>(){

    override suspend fun buildUseCase(): List<Int> {
        //TODO
        return emptyList()
    }
}