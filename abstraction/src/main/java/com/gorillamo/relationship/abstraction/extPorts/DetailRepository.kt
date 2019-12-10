package com.gorillamo.relationship.abstraction.extPorts

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Detail
import com.gorillamo.relationship.abstraction.dto.Relationship

/**
 * An abstraction for the Detail repository
 */
interface DetailRepository {

    fun getDetailFor(relationship: Relationship): LiveData<out List<Detail>?>

}