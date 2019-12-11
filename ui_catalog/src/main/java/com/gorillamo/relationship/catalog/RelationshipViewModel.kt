package com.gorillamo.relationship.catalog

import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.catalog.Coroutines.io
import com.gorillamo.relationship.catalog.Coroutines.ioThenMain

public class RelationshipViewModel(val repository:RelationshipRepository): ViewModel() {

//    private var repository: RelationshipRepository
    private val _tasks = MutableLiveData<List<Relationship>>()

    //the object on which we can observe changes
    val tasks: LiveData<List<Relationship>> get() = _tasks

    init {

        //get the db.
//        val db = ApplicationDatabase.getDatabase(application)
//        val relationshipDao = db.relationshipDao()
//        repository = RelationshipRepositoryImpl.getInstance(relationshipDao)

    }

    @UiThread
    fun loadAllRelationships(): LiveData<out List<Relationship>?>? {

/*
        ioThenMain({}){

            it?.let {

                it.value?.forEach {
                    Log.d("Fetch Results","${it.name} @ ${it.timeLastContacted}")
                }

                _tasks.value = it.value
            }?:run{
                Log.d("Fetch Results","repo returned null Live Data Objectt")

            }
        }
*/
        return repository.getRelationshipsLive()
    }

    fun insert(relationship: Relationship){
       io{
           Log.d("Inserting","${relationship.name} @ ${relationship.timeLastContacted}")
            repository.insertOrUpdateRelationship(relationship)
        }
    }

}