package com.gorillamo.relationships.domain

import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationships.domain.adapters.RelationshipRepositoryAdapter
import org.koin.core.module.Module
import org.koin.dsl.module

object DomainModules {

    /**
     * This module contains an instance of RelationshipRepository,
     * which will be provided to RelationshipViewModel by Koin
     */
    private val repositoryModule = module{
        single<RelationshipRepository>{ RelationshipRepositoryAdapter(get())}
    }

    val modules: List<Module> = listOf(repositoryModule)

}