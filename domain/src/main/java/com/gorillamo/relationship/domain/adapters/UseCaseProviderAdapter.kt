package com.gorillamo.relationship.domain.adapters

import androidx.lifecycle.LiveData
//import com.gorillamo.details.DetailsRepository
import com.gorillamo.relationship.domain.dto.Relationship
import com.gorillamo.relationship.domain.extPorts.*
import com.gorillamo.relationship.domain.usecase.DeleteRelationshipUseCase
import com.gorillamo.relationship.domain.usecase.LoadRelationshipsUseCase
import com.gorillamo.relationship.domain.usecase.LoadTodaysRelationshipUseCase
import com.gorillamo.relationship.domain.usecase.SaveRelationshipUseCase


/**
 * The use case layer
 */
internal class UseCaseProviderAdapter(

    override val repository: RelationshipRepository,
    override val detailRepository: DetailRepository<Relationship>

):UseCaseProvider
{

    override val saveRelationship: UseCaseWithParams<Relationship, Long>
        get() = SaveRelationshipUseCase(repository)

    override val loadRelationship: UseCaseSync<LiveData<out List<Relationship>>>
        get() = LoadRelationshipsUseCase(repository)

    override val deleteRelationShip: UseCaseWithParams<String, Int>
        get() = DeleteRelationshipUseCase(repository)

    override val getTodaysRelationship: UseCaseSync<LiveData<out List<Relationship>>>
        get() = LoadTodaysRelationshipUseCase(repository)



}