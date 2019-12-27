package com.gorillamo.relationship.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gorillamo.relationship.abstraction.dto.Relationship

@Entity(tableName = "RelationshipsTable")
data class  RelationshipDatabaseObject(

    @PrimaryKey(autoGenerate = true)
    override var id:Int,
    override val name: String = "",
    override val lastContacted:Long = 0L,
    override val frequency: Float = 0F,
    override val ready:Boolean = false

):Relationship