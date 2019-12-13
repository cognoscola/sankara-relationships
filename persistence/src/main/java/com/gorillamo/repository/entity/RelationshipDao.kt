package com.gorillamo.repository.entity

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RelationshipDao:BaseDao<RelationshipDatabaseObject> {

    @Query("SELECT * FROM RelationshipsTable")
    fun getAllRelationship(): LiveData<List<RelationshipDatabaseObject>>

    @Query("SELECT * FROM RelationshipsTable")
    fun getTodaysRelationship(): LiveData<List<RelationshipDatabaseObject>>

    /**
     * returns the number of rows affected by this delete
     */
    @Query("DELETE FROM RelationshipsTable")
//    @Query("DELETE  FROM RelationshipTable WHERE name = :name")
    suspend fun delete(): Int
}