package com.gorillamo.relationship.persistence.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RelationshipDao:
    BaseDao<RelationshipDatabaseObject> {

    @Query("SELECT * FROM RelationshipsTable")
    fun getAllRelationship(): LiveData<List<RelationshipDatabaseObject>>

    @Query("SELECT * FROM RelationshipsTable")
    fun getTodaysRelationship(): LiveData<List<RelationshipDatabaseObject>>

    /**
     * returns the number of rows affected by this delete
     */
    @Query("DELETE FROM RelationshipsTable WHERE name = :name")
    suspend fun delete(name:String): Int
}