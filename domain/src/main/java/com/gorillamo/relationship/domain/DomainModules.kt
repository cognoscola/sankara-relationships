package com.gorillamo.relationship.domain

import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseProvider
import com.gorillamo.relationship.domain.adapters.RelationshipRepositoryAdapter
import com.gorillamo.relationship.domain.adapters.UseCaseProviderAdapter
import com.gorillamo.scheduler.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

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

    private val schedulerModule = module {
        single<Scheduler<Relationship>> {
            Scheduler.getInstance {
                SchedulingItem(
                    it,
                    PointInTime(it.lastContacted),
                    Value(it.count),
                    Value(it.range),
                    Identifier(it.id)
                )
            }
        }

        single<CoroutineDispatcher>{ Dispatchers.IO }
    }

    private val useCaseModule = module {
        single<UseCaseProvider> { UseCaseProviderAdapter(get())}
    }


    val modules: List<Module> = listOf(repositoryModule, schedulerModule, useCaseModule)

}