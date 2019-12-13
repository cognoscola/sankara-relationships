package com.gorillamo.repository.adapters

import android.util.Log
import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationships.domain.ports.RelationshipDaoPort
import com.gorillamo.repository.entity.RelationshipDao
import com.gorillamo.repository.entity.RelationshipDatabaseObject

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
            relationshipDao.insertOrReplace(RelationshipDatabaseObject( null,
                relationship.name!!, relationship.timeLastContacted!!))
    }

    override fun getBooksLive():LiveData<out List<Relationship>?>{

        Log.d("$tag getBooksLive","DaoAdapter received fetch command")

        return relationshipDao.getAllRelationship()
    }

}