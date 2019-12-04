package com.gorillamo.relationships.model

import com.gorillamo.relationships.model.Relationship
import java.util.*

/**
 * A relationship repository that provides a single source
 * of relationship objects for the app
 */
interface RelationshipRepository  {


    /**
     * Fetch all relationships
     */
    fun getAllRelationships():List<Relationship>


    /**
     * Fetch relationships for today
     */
    fun getRelationBasedOnDay(cal:Calendar):List<Relationship>



}
