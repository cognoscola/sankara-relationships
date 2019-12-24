package com.gorillamo.relationship.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gorillamo.relationship.abstraction.dto.Relationship

@Entity(tableName = "RelationshipsTable")
data class  RelationshipDatabaseObject(

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null,
    override val name: String = "",
    override val timeLastContacted:Long = 0L,
    override val frequency: Float? = 0F

):Relationship