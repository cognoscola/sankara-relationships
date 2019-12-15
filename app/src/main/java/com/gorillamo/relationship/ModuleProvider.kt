package com.gorillamo.relationship

import com.gorillamo.relationship.domain.DomainModules
import com.gorillamo.relationship.persistence.PersistenceModules
import org.koin.core.module.Module
import org.koin.dsl.module

object ModuleProvider {

    private val appModules = module {
//        single<ContentResolver> { androidContext().contentResolver }
    }

    val modules: List<Module>
        get() {
            return ArrayList<Module>().apply {
                add(appModules)
                addAll(DomainModules.modules)
                addAll(PersistenceModules.modules)
//                addAll(NetworkModules.modules)
            }
        }
}