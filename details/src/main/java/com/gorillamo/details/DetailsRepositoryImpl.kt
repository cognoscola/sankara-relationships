package com.gorillamo.details

internal class DetailsRepositoryImpl<T>() :DetailsRepository<T>{


    override suspend fun add(entity: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun fetch(entity: T, page: Int, count: T): List<Detail> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun remove(entity: T) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}