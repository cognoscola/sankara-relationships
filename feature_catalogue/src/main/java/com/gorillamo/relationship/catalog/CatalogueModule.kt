package com.gorillamo.relationship.catalog

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

//TODO create a release version of this
object CatalogueModule {
    fun load() {
        return load
    }


    val mockMode:Boolean = false

    val load by lazy{

        loadKoinModules(viewModelModules)
    }



    private val viewModelModules = module{
        viewModel { RelationshipViewModel(get()) }
    }


}