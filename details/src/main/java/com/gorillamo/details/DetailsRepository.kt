package com.gorillamo.details

/**
 * An interface that marks the functions of a details repository
 */
interface DetailsRepository <T> {

    /**
     * Add a detail to the given entity
     */
    suspend fun add(entity:T)

    /**
     * Remove a detail from the entity
     */
    suspend fun remove(entity:T)

    /**
     * Fetch a list of details for the give entity
     * @param page is the page number
     * @param size is the page size
     */
    suspend fun fetch(entity:T, page:Int, count:T):List<Detail>

    companion object{

        fun <T> getInstance():DetailsRepository<T>{
           return  DetailsRepositoryImpl<T>()
        }
    }
}