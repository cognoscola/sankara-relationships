package com.gorillamo.relationships.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "RelationshipsTable")
data class RelationshipDatabaseObject(

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    val name: String = "",
    val timeLastContacted:Long = 0L
)