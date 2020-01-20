package com.gorillamo.details.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gorillamo.definitions.PointInTime
import com.gorillamo.details.Detail

/**
 * we''ll use this table to also get the History
 */
//TODO we need to convert PointInTime
@Entity(tableName = "DetailTable")
internal data class DetailDatabaseObject(

    @PrimaryKey(autoGenerate = true)
    var id:Int,
    var entityId:Int,
    override val created: PointInTime,
    override val detail: String

): Detail