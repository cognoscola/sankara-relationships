package com.gorillamo.relationship.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gorillamo.relationship.domain.dto.Relationship

@Entity(tableName = "RelationshipsTable")
data class  RelationshipDatabaseObject(

    @PrimaryKey(autoGenerate = true)
    override var id:Int,
    override val name: String = "",
    override val lastContacted:Long = 0L,
    override val count: Int = 1,
    override val range: Int = 1,
    override val ready:Boolean = false

):Relationship