package com.gorillamo.repository.entity

import android.app.Application
import androidx.annotation.UiThread
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.gorillamo.repository.RelationshipRepository
import com.gorillamo.repository.RelationshipRepositoryImpl
import com.gorillamo.repository.core.Coroutines.io
import com.gorillamo.repository.core.Coroutines.ioThenMain


public class RelationshipViewModel(application:Application):AndroidViewModel(application) {

    private var repository:RelationshipRepository
    private val _tasks = MutableLiveData<List<RelationshipDatabaseObject>>()

    //the object on which we can observe changes
    val tasks: LiveData<List<RelationshipDatabaseObject>> get() = _tasks

    init {

        //get the db.
        val db = ApplicationDatabase.getDatabase(application)
        val relationshipDao = db.relationshipDao()
        repository = RelationshipRepositoryImpl.getInstance(relationshipDao)

    }

    @UiThread
    fun loadAllRelationships(): LiveData<List<RelationshipDatabaseObject>> {
        ioThenMain({repository.getAllRelationships()}){
            _tasks.value = it
        }
        return tasks
    }

    fun insert(relationshipDatabaseObject: RelationshipDatabaseObject){
       io{
            repository.insert(relationshipDatabaseObject)
        }
    }

}