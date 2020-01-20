package com.gorillamo.relationship.persistence.entity.detail

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gorillamo.relationship.domain.dto.Detail

@Entity(tableName = "DetailTable")
data class DetailDatabaseObject(

    @PrimaryKey(autoGenerate = true)
    var id:Int,
    override var entityId: Int,
    override var timeAdded: Long,
    override var description: String

):Detail