package com.gorillamo.relationship.domain.extPorts

import androidx.lifecycle.LiveData
import com.gorillamo.details.DetailsRepository
import com.gorillamo.relationship.domain.dto.Relationship

/**
 * Think of a repository as a a collection of functions available to the app.
 * The repository should "morph" i.e. have a collection of accessible methods
 * for the app, according to what it needs. This "morphing" is just us, the developer
 * adding components to it.
 * E.g. the relation ship is an entity that has details. So we'll add the details component
 *
 */
interface RelationshipRepository{

    suspend fun insertOrUpdateRelationship(relationship: Relationship):Long

    fun getRelationshipsLive(): LiveData<out List<Relationship>?>

    fun getTodaysRelationships(): LiveData<out List<Relationship>?>

    suspend fun getRelationshipAsync(): List<Relationship>

    suspend fun deleteRelationship(name:String):Int

}