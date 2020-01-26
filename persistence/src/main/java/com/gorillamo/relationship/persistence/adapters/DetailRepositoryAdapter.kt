package com.gorillamo.relationship.persistence.adapters

import com.gorillamo.relationship.abstraction.dto.Detail
import com.gorillamo.relationship.abstraction.extPorts.DetailRepository
import com.gorillamo.relationship.persistence.entity.DetailDao

/**
 * This is an implementation of the repository object
 */
internal class DetailRepositoryAdapter<T>(
    private val detailDao: DetailDao
): DetailRepository<T> {

    override suspend fun add(entity: T, detail: Detail) {


    }

    override suspend fun fetch(
        entity: T,
        page: Int,
        count: T
    ): List<Detail> {

        return emptyList()
    }

    override suspend fun remove(entity: T, detail: Detail) {

    }


}