package com.gorillamo.relationship.domain.usecase

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCase
import com.gorillamo.relationship.abstraction.extPorts.UseCaseSync
import com.gorillamo.scheduler.DaySchedulerAdapter
import com.gorillamo.scheduler.SchedulerPort

class LoadTodaysRelationshipUseCase (

    private val repository: RelationshipRepository
//    private val schedulerPort: SchedulerPort

): UseCaseSync<LiveData<out List<Relationship>>>(){


    override fun buildUseCase(): LiveData<out List<Relationship>> {

        /**
         * Get the repo items
         */
        return repository.getTodaysRelationships()

        /**
         * Give the items to the scheduler for scheduling
         *//*
        val lineUp = items.map {
            SchedulerPort.toSchedulingItems(it.id,it.frequency!!,it.timeLastContacted!!)
        }
        val scheduledItemsIds = schedulerPort.getDueItems(lineUp)

        *//**
         * Return a list of scheduled items
         *//*
        return items.filter { scheduledItemsIds.contains(it.id) }*/
    }
}