package com.gorillamo.relationship.persistence.adapters

import android.util.Log
import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.domain.ports.RelationshipDaoPort
import com.gorillamo.relationship.persistence.entity.RelationshipDao
import com.gorillamo.relationship.persistence.entity.RelationshipDatabaseObject

internal class RelationshipDaoAdapter(
    private val relationshipDao: RelationshipDao
):RelationshipDaoPort
{

    @Suppress("unused")
    private val tag:String = RelationshipDaoAdapter::class.java.name

    override suspend fun insertOrUpdate(relationship: Relationship):Long {

        return if (relationship is RelationshipDatabaseObject)
            relationshipDao.insertOrReplace(relationship)
        else
            relationshipDao.insertOrReplace(
                RelationshipDatabaseObject(
                    relationship.id,
                    relationship.name,
                    relationship.lastContacted,
                    relationship.count,
                    relationship.range,
                    relationship.ready
                )
            )
    }

    override fun getRelationshipsLive():LiveData<out List<Relationship>?>{

        Log.d("$tag getBooksLive","DaoAdapter received fetch command")
        return relationshipDao.getAllRelationship()
    }


    override fun getTodaysRelationshipLive():LiveData<out List<Relationship>?>{
        return relationshipDao.getTodaysRelationship()
    }

    override suspend fun deleteRelationship(name:String):Int {
        return relationshipDao.delete(name)

    }
}