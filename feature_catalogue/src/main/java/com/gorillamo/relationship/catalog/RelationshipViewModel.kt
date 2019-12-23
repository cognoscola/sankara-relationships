package com.gorillamo.relationship.catalog

import android.util.Log
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gorillamo.relationship.abstraction.dto.Relationship
//import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseProvider
import com.gorillamo.relationship.catalog.Coroutines.io
import com.gorillamo.relationship.catalog.Coroutines.ioThenMain
import com.gorillamo.relationship.ui.catalogue.RelationshipView

public class RelationshipViewModel(

    val useCaseProvider: UseCaseProvider

): ViewModel(),RelationshipView {


    fun loadAllRelationships(): LiveData<out List<Relationship>?>? {

        return useCaseProvider.loadRelationship.execute()
    }

    fun insert(relationship: Relationship){
       io{
           Log.d("Inserting","${relationship.name} @ ${relationship.timeLastContacted}")
           useCaseProvider.saveRelationship.execute(relationship)
        }
    }

    //TODO change to delete one!
    fun deleteRelationship(relationship: Relationship){
        io{
            useCaseProvider.deleteRelationShip.execute(relationship)
        }
    }

    override fun allRelationshipsClicked() {
        for(i in 0..10){
            insert(
                object: Relationship{
                    override val name: String?
                        get() = "name $i"
                    override val timeLastContacted: Long?
                        get() = today() - days(i)
                }
            )
        }

    }


    private fun today() = System.currentTimeMillis()

    private fun days(day:Int) = oneDayInMillis() * day

    private fun oneDayInMillis():Long{
        return 1000 * 60 * 60 * 24
    }

    override fun todayClicked() {
        deleteRelationship(
            object: Relationship{
                override val name: String?
                    get() = "name 0"
                override val timeLastContacted: Long?
                    get() = System.currentTimeMillis()
            }
        )
    }

}