package com.gorillamo.details

import com.gorillamo.details.data.DetailDao

internal class DetailsRepositoryImpl<T>(

//    val detailDao: DetailDao

) :DetailsRepository<T>{


    override suspend fun add(entity: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetch(entity: T, page: Int, count: T): List<Detail> {
        return emptyList()
//        return detailDao.getAllRelationshipList()
    }

    override suspend fun remove(entity: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}