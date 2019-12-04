package com.gorillamo.relationships

import com.gorillamo.relationships.model.RelationshipRepository
import java.lang.RuntimeException
import java.util.*

class RelationshipPresenter(
    private val view: SearchRelationshipsContract.RelationshipView,
    val repository: RelationshipRepository,
    val cal:Calendar):SearchRelationshipsContract.RelationshipPresenter

{

    override fun loadToday() {
        try {
            cal.timeInMillis = System.currentTimeMillis()
            val list = repository.getRelationBasedOnDay(cal)
            view.displayRelationships(list)
        }catch (e:RuntimeException){
            view.displayError()
        }
    }

    override fun loadAll() {

        try {
            val list = repository.getAllRelationships()
            view.displayRelationships(list)

        } catch (e: RuntimeException) {
            view.displayError()
        }
    }




}