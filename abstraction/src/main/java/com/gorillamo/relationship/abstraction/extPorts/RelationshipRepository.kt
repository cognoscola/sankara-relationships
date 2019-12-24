package com.gorillamo.relationship.abstraction.extPorts

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship

/**
 * This is an abstraction of the repository object used by domain
 */
interface RelationshipRepository {

    fun getRelationshipsLive(): LiveData<out List<Relationship>?>

    fun getTodaysRelationship(): LiveData<out List<Relationship>?>

    suspend fun insertOrUpdateRelationship(relationship: Relationship):Long

    suspend fun deleteRelationship(name:String):Int

}