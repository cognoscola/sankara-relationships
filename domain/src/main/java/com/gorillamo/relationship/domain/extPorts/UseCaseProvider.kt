package com.gorillamo.relationship.domain.extPorts

import androidx.lifecycle.LiveData
import com.gorillamo.details.DetailsRepository
import com.gorillamo.relationship.domain.dto.Relationship

interface UseCaseProvider {

    val repository:RelationshipRepository

    val detailsRelationship:DetailsRepository<Relationship>

    val loadRelationship:UseCaseSync<LiveData<out List<Relationship>>>

    val getTodaysRelationship:UseCaseSync<LiveData<out List<Relationship>>>

    val saveRelationship:UseCaseWithParams<Relationship,Long>

    val deleteRelationShip:UseCaseWithParams<String,Int>





}