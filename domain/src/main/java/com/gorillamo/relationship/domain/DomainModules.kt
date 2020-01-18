package com.gorillamo.relationship.domain

import com.gorillamo.details.DetailsRepository
import com.gorillamo.honeycomb.Hive
import com.gorillamo.relationship.domain.dto.Relationship
import com.gorillamo.relationship.domain.extPorts.RelationshipRepository
import com.gorillamo.relationship.domain.extPorts.UseCaseProvider
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
        single<RelationshipRepository>{ RelationshipRepositoryAdapter(
            get()) //is a DaoAdapter, Koin will provide it from Persistence.
        }
    }

    private val detailsModule = module{

        single<DetailsRepository<Relationship>>{
            DetailsRepository.getInstance<Relationship>()
        }
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
        single<UseCaseProvider> { UseCaseProviderAdapter(get(),get())}
    }

    private val hiveModule = module{
        single<Hive>{

            //A hive is going to replace a "repository" module.
            //so Hive is going to be a "generic" repositoy,
            //with the ability to add properties to it.
            Hive.defineInstance<Relationship>()

        }
    }

    val modules: List<Module> = listOf(repositoryModule, schedulerModule,detailsModule, useCaseModule, hiveModule)

}