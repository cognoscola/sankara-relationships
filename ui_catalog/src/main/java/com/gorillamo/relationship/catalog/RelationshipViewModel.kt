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


    @UiThread
    fun loadAllRelationships(): LiveData<out List<Relationship>?>? {

        return repository.getRelationshipsLive()
    }

    fun insert(relationship: Relationship){
       io{
           Log.d("Inserting","${relationship.name} @ ${relationship.timeLastContacted}")
            repository.insertOrUpdateRelationship(relationship)
        }
    }

}