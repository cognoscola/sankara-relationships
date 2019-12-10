package com.gorillamo.repository.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RelationshipDao {

    @Query("SELECT * FROM RelationshipsTable")
    fun getAllRelationship(): List<RelationshipDatabaseObject>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(relationship:RelationshipDatabaseObject):Long

}