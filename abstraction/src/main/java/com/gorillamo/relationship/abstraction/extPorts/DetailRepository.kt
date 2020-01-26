package com.gorillamo.relationship.abstraction.extPorts

import com.gorillamo.relationship.abstraction.dto.Detail


/**
 * An abstraction for the Detail repository
 */
interface DetailRepository<T> {

    /**
     * Add a detail to the given entity
     */
    suspend fun add(entity:T,detail:Detail)

    /**
     * Remove a detail from the entity
     */
    suspend fun remove(entity:T,detail:Detail)

    /**
     * Fetch a list of details for the give entity
     * @param page is the page number
     * @param size is the page size
     */
    suspend fun fetch(entity:T, page:Int, count:T):List<Detail>


}