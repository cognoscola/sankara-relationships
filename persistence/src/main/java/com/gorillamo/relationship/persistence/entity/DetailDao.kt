package com.gorillamo.relationship.persistence.entity

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DetailDao:
    BaseDao<DetailDatabaseObject> {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertOrReplace(item: DetailDatabaseObject): Long

    /*@Query("SELECT * FROM RelationshipsTable")
    fun getAllRelationship(): LiveData<List<DetailDatabaseObject>>*/

    @Query("SELECT * FROM DetailTable")
    fun getAllDetails(): List<DetailDatabaseObject>

    /**
     * returns the number of rows affected by this delete
     */
    @Query("DELETE FROM DetailTable WHERE entityId = :entityId")
    suspend fun delete(entityId:String): Int
}