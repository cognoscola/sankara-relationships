package com.gorillamo.repository

import androidx.annotation.WorkerThread
import java.util.*
import com.gorillamo.repository.entity.RelationshipDao
import com.gorillamo.repository.entity.RelationshipDatabaseObject

/**
 * The implementation of the repository class. Its job is to provide a "single source of truth"
 * to the app. It will try and fetch info from the data source now.
 */
class RelationshipRepositoryImpl constructor(val dao: RelationshipDao):
    RelationshipRepository {


    @WorkerThread
    override fun getAllRelationships(): List<RelationshipDatabaseObject> {

        return dao.getAllRelationship()
    }

    override fun getRelationBasedOnDay(cal: Calendar): List<Relationship>{
        return emptyList()
    }

    @WorkerThread
    override fun insert(relationship: RelationshipDatabaseObject) {
        dao.insertTask(relationship)
    }


    companion object{

        private var INSTANCE: RelationshipRepositoryImpl? = null

        fun getInstance(dao: RelationshipDao): RelationshipRepository {
            return INSTANCE
                ?: RelationshipRepositoryImpl(
                    dao
                )
        }
    }

}