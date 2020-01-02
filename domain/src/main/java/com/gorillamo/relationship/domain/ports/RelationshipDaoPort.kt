package com.gorillamo.relationship.domain.ports

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship

/**
 * This is a port available to the outside world
 */
interface RelationshipDaoPort{

    fun getRelationshipsLive(): LiveData<out List<Relationship>?>

    fun getTodaysRelationshipLive():LiveData<out List<Relationship>?>

    suspend fun getRelationshipAsync():List<Relationship>

    suspend fun insertOrUpdate(relationship: Relationship):Long

    suspend fun deleteRelationship(name:String):Int

}