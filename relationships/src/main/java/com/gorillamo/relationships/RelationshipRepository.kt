package com.gorillamo.relationships

/**
 * A relationship repository that provides a single source
 * of relationship objects for the app
 */
interface RelationshipRepository  {


    /**
     * Fetch all relationships
     */
    fun getAllRelationships():List<Relationship>




}
