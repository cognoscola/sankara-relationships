package com.gorillamo.relationship.abstraction.extPorts

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship

interface UseCaseProvider {

    val repository:RelationshipRepository

    val saveRelationship:UseCaseWithParams<Relationship,Long>

    val loadRelationship:UseCaseSync<LiveData<out List<Relationship>>>

    val deleteRelationShip:UseCaseWithParams<String,Int>

}