package com.gorillamo.relationships.domain.adapters


import androidx.lifecycle.LiveData
import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseProvider
import com.gorillamo.relationship.abstraction.extPorts.UseCaseSync
import com.gorillamo.relationship.abstraction.extPorts.UseCaseWithParams
import com.gorillamo.relationships.domain.usecase.LoadRelationshipsUseCase
import com.gorillamo.relationships.domain.usecase.SaveRelationshipUseCase

/**
 * The use case layer
 */
internal class UseCaseProviderAdapter(

    override val repository: RelationshipRepository

):UseCaseProvider
{

    override val saveRelationship: UseCaseWithParams<Relationship, Long>
        get() = SaveRelationshipUseCase(repository)

    override val loadRelationship: UseCaseSync<LiveData<out List<Relationship>>>
        get() = LoadRelationshipsUseCase(repository)

}