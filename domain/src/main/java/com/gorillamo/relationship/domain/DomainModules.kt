package com.gorillamo.relationship.domain

import com.gorillamo.relationship.abstraction.extPorts.RelationshipRepository
import com.gorillamo.relationship.abstraction.extPorts.UseCaseProvider
import com.gorillamo.relationship.domain.adapters.RelationshipRepositoryAdapter
import com.gorillamo.relationship.domain.adapters.UseCaseProviderAdapter
import com.gorillamo.scheduler.DaySchedulerAdapter
import com.gorillamo.scheduler.SchedulerPort
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

    private val schedulerModule = module{
        single<SchedulerPort>{ DaySchedulerAdapter()}
    }
    private val useCaseModule = module {
        single<UseCaseProvider> { UseCaseProviderAdapter(get(),get())}
    }


    val modules: List<Module> = listOf(repositoryModule, useCaseModule)

}