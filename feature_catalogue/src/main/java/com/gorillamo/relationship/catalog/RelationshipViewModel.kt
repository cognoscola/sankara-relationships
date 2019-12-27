package com.gorillamo.relationship.catalog

import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.UseCaseProvider
import com.gorillamo.relationship.catalog.Coroutines.io
import com.gorillamo.relationship.catalog.Coroutines.ioThenMain
import com.gorillamo.relationship.ui.catalogue.RelationshipView

public class RelationshipViewModel(

    val useCaseProvider: UseCaseProvider

): ViewModel() {


    fun laodTodaysRelationships():LiveData<out List<Relationship>?>? {
        return useCaseProvider.getTodaysRelationship.execute()
    }

    fun loadAllRelationships(): LiveData<out List<Relationship>?>? {

        return useCaseProvider.loadRelationship.execute()
    }

    fun insert(relationship: Relationship){
       io{
           useCaseProvider.saveRelationship.execute(relationship)
        }
    }

    //TODO change to delete one!
    fun deleteRelationship(name:String){
        io{
            useCaseProvider.deleteRelationShip.execute(name)
        }
    }

}