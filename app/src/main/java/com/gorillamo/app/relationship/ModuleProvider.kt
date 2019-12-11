package com.gorillamo.app.relationship

import android.content.ContentResolver
import com.gorillamo.relationships.domain.DomainModules
import com.gorillamo.repository.PersistenceModules
import org.koin.android.ext.koin.androidContext
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