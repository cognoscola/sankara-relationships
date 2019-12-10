package com.gorillamo.relationships.domain.ports

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship

/**
 * This is a port available to the outside world
 */
interface RelationshipDaoPort{

    fun getBooksLive(): LiveData<out List<Relationship>?>

}