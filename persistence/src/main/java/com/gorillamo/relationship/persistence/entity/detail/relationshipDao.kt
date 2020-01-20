package com.gorillamo.relationship.persistence.entity.detail

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

import com.gorillamo.relationship.domain.dto.Detail
import com.gorillamo.relationship.persistence.entity.BaseDao
import com.gorillamo.relationship.persistence.entity.RelationshipDatabaseObject

@Dao
interface DetailDao:
    BaseDao<RelationshipDatabaseObject> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(item: DetailDatabaseObject): Long

    /*@Query("SELECT * FROM RelationshipsTable")
    fun getAllRelationship(): LiveData<List<DetailDatabaseObject>>*/

    @Query("SELECT * FROM DetailTable")
    fun getAllRelationshipList(): List<DetailDatabaseObject>

    /**
     * returns the number of rows affected by this delete
     */
    @Query("DELETE FROM DetailTable WHERE entityId = :entityId")
    suspend fun delete(entityId:String): Int
}