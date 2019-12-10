package com.gorillamo.repository

import com.gorillamo.repository.entity.RelationshipDatabaseObject
import java.util.*

/**
 * A relationship repository that provides a single source
 * of relationship objects for the app
 */
interface RelationshipRepository  {


    /**
     * Fetch all relationships
     */
    //TODO convert from Databoase object to a Domain Model Object. Conversion must happen in repo
    fun getAllRelationships():List<RelationshipDatabaseObject>


    /**
     * Fetch relationships for today
     */
    //TODO return RESULT object rather than empty list
    fun getRelationBasedOnDay(cal:Calendar):List<Relationship>


    //insert Domain object instead of DB object
    fun insert(relationship:RelationshipDatabaseObject)


}
