package com.gorillamo.repository.entity

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RelationshipDao:BaseDao<RelationshipDatabaseObject> {

    @Query("SELECT * FROM RelationshipsTable")
    fun getAllRelationship(): LiveData<List<RelationshipDatabaseObject>>

    /*@Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTask(relationship:RelationshipDatabaseObject):Long*/

}