package com.gorillamo.relationship.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.UseCaseProvider
import com.gorillamo.relationship.catalog.Coroutines.io
import com.gorillamo.relationship.ui.catalogue.RelationshipView

public class RelationshipViewModel(

    val useCaseProvider: UseCaseProvider

): ViewModel(),RelationshipView {

    val data = MediatorLiveData<List<Relationship>>()

    val todaysRelationship = useCaseProvider.getTodaysRelationship.execute()
    val allRelationships = useCaseProvider.loadRelationship.execute()

    var today = true

    fun data():LiveData<List<Relationship>?>?{

        data.addSource(todaysRelationship) { result ->
            result?.let { if (today) { data.value = it } }
        }

        data.addSource(allRelationships) { result ->
            result?.let {
                if (!today) { data.value = it }
            }
        }

        return data
    }

    private fun insert(relationship: Relationship){
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

    override fun todayClicked() {

        today = true
        todaysRelationship.value?.let { data.value = it }
    }

    override fun allRelationshipsClicked() {

        today = false
        allRelationships.value?.let { data.value = it }
    }

    override fun addClicked(name: String, frequency: Float) {
        insert(
            object: Relationship {
                override val name: String get() = name
                override val lastContacted: Long get() = 0
                override val ready: Boolean get() = true
                override val frequency: Float get() = frequency
            }
        )
    }

    override fun deleteClicked(name: String) {
        deleteRelationship(name)
    }

}