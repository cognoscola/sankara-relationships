package com.gorillamo.relationship

import com.gorillamo.relationship.abstraction.dto.Relationship
import com.gorillamo.relationship.domain.DomainModules
import com.gorillamo.relationship.persistence.PersistenceModules
import com.gorillamo.scheduler.*
import org.koin.core.module.Module
import org.koin.dsl.module

object ModuleProvider {


    private val appModules = module {
       /* single<Scheduler<Relationship>>{

            Scheduler.getInstance {

                SchedulingItem(
                    item = it,
                    timeLastInteracted = PointInTime(it.lastContacted),
                    count = Value(it.count),
                    range = Value(it.range),
                    id = Identifier(it.id)

                ) }
        }*/
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