package com.gorillamo.relationship.ui.catalogue

interface RelationshipView {

    fun todayClicked()

    fun allRelationshipsClicked()

    fun addClicked(name:String, frequency:Float)

    fun deleteClicked(name:String)

}