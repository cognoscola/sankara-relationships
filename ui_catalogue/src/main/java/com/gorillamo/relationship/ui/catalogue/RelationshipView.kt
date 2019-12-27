package com.gorillamo.relationship.ui.catalogue

import com.gorillamo.relationship.ui.catalogue.RelationshipItemAdapter.*

interface RelationshipView {

    fun todayClicked()

    fun allRelationshipsClicked()

    fun addClicked(item: RelationshipItem)

    fun deleteClicked(name:String)

    fun updateClicked(item: RelationshipItem)

}