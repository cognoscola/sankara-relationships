package com.gorillamo.relationship.catalog

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object CatalogueModule {
    fun load() = load

    val load by lazy{

        loadKoinModules(viewModelModules)
    }



    private val viewModelModules = module{
        viewModel { RelationshipViewModel(get()) }
    }


}