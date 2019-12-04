package com.gorillamo.relationships

class RelationshipPresenter(
    val view: SearchRelationshipsContract.RelationshipView,
    val repository:RelationshipRepository):SearchRelationshipsContract.RelationshipPresenter
{


    override fun loadToday() {

        val list = repository.getAllRelationships()
        view.displayRelationships(list)

    }

    override fun loadAll() {
        val list = repository.getAllRelationships()
        view.displayRelationships(list)
    }




}