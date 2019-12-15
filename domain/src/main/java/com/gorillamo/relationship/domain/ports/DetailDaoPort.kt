package com.gorillamo.relationship.domain.ports

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Detail
import com.gorillamo.relationship.abstraction.dto.Relationship

/**
 * Port to be implemented by outside world
 */
interface DetailDaoPort{

    fun getDetailsFor(relationship: Relationship):LiveData<out List<Detail>?>

}