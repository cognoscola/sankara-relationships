package com.gorillamo.relationships.domain.adapters

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Detail
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.DetailRepository
import com.gorillamo.relationships.domain.ports.DetailDaoPort

internal class DetailRepositoryAdapter(
    private val detailDaoPort: DetailDaoPort
):DetailRepository{

    override fun getDetailFor(relationship: Relationship): LiveData<out List<Detail>?> {
        return detailDaoPort.getDetailsFor(relationship)
    }
}