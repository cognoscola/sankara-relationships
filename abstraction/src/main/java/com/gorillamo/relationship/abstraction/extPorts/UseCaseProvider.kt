package com.gorillamo.relationship.abstraction.extPorts

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship

interface UseCaseProvider {

    val repository:RelationshipRepository

    val detailRepository:DetailRepository<Relationship>


    val loadRelationship:UseCaseSync<LiveData<out List<Relationship>>>

    val getTodaysRelationship:UseCaseSync<LiveData<out List<Relationship>>>

    val saveRelationship:UseCaseWithParams<Relationship,Long>

    val deleteRelationShip:UseCaseWithParams<String,Int>

}