package com.gorillamo.relationship.persistence

import androidx.lifecycle.LiveData
import com.gorillamo.relationship.domain.dto.Detail

import com.gorillamo.relationship.domain.dto.Relationship
import com.gorillamo.relationship.domain.extPorts.DetailRepository
import com.gorillamo.relationship.domain.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.ports.RelationshipDaoPort
import com.gorillamo.relationship.persistence.entity.detail.DetailDao

/**
 * This is an implementation of the repository object
 */
internal class DetailRepositoryAdapter<T>(
    private val detailDao: DetailDao
):DetailRepository<T>
{

    override suspend fun add(entity: T,detail: Detail) {


    }

    override suspend fun fetch(
        entity: T,
        page: Int,
        count: T
    ): List<com.gorillamo.relationship.domain.dto.Detail> {

        return emptyList()
    }

    override suspend fun remove(entity: T,detail: Detail) {

    }



}