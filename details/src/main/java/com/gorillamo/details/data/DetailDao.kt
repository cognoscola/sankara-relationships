package com.gorillamo.details.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
internal interface DetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrReplace(item: DetailDatabaseObject): Long

    /*@Query("SELECT * FROM RelationshipsTable")
    fun getAllRelationship(): LiveData<List<DetailDatabaseObject>>*/

    @Query("SELECT * FROM DetailTable")
    fun getAllRelationshipList(): List<DetailDatabaseObject>

    /*@Query("SELECT * FROM RelationshipsTable WHERE ready = 1")
    fun getTodaysRelationship(): LiveData<List<DetailDatabaseObject>>*/

    /**
     * returns the number of rows affected by this delete
     */
    @Query("DELETE FROM DetailTable WHERE entityId = :entityId")
    suspend fun delete(entityId:String): Int
}